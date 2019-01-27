import java.net.*;
import java.io.*;
import java.util.*;
import java.io.File;
import static java.lang.System.*;

/**
 * to run on the miners' computers. Remotely stores a log of all transactions
 */
public class Miner{
    private static final int TRANLENGTH=128;
    private static int sizeOfBlockChain;
    private static Socket me;
    private static String menu="";
    private static boolean updated=false;
    
    public static void main(String[] args)throws Exception{
	me=new Socket();
	Runnable r = new Runnable(){
	    @Override
	    public void run(){
		try{
		    while(true) receiveTran();
		}catch(Exception ex){
		    out.print("Problem receiveing transactions because: "+ex+": "+ex.getMessage()+"\n> ");
		}
	    }
        };
 	Thread receive = new Thread(r);
	Scanner kb = new Scanner(in);
	String menu="";
	out.print("> ");
	while(true){
	    menu=kb.nextLine().trim();
        switch(menu){
            case "start":
		try{
		    out.println("Trying to connect...");
		    me = new Socket(InetAddress.getByName("10.204.88.6"), 80); // connect to main server
		    out.println("Got connection!");
		}catch(Exception ex){
		    out.print("Could not connect to Server because: "+ex+": "+ex.getMessage()+"\n> ");
		}
                updateBlockChain();
                receive.start();
		out.print("Waiting for transactions\n> ");
		updated=true;
                break;
	    case "close":
		receive.stop();
		me.close();
		updated=false;
		exit(0);
	    case "help":
	    case "?":
		printMenu();
	        break;
	    default:
		out.print("Type 'help' or '?' for a list of commands\n> ");
        }
	}
    }
    /**
     * Deletes the my block chain
     * @author Mohit Bhole
     */
    private static void deleteBlockChain()throws Exception{
	File deleteBlockChain = new File("blockChain.bcf");
        if(deleteBlockChain.delete())
            System.out.print("Files successfully deleted.\n> ");
        else
            System.out.print("File not deleted.\n> ");	
    }
    /**
     * Receives one transaction from the bank server
     * or, if there server has requsted, send my blockchain to the server
     * @author Charles Jackson
     */
    private static void receiveTran()throws Exception{
	InputStream nin=me.getInputStream();       // to read messages sent from the server
	byte[] cmd=new byte[1];		            // store the command from the server
	if(nin.available() > 0)	                    // if there is a command from the server
	    nin.read(cmd);		            // read the command character
	if((char)cmd[0]=='t'){		            // t for receive Transaction. this receives one block
	    byte[] tranBytes = new byte[TRANLENGTH];// to store the new transaction
	    nin.read(tranBytes);	            // read the new transaction
	    String tran=new String(tranBytes);      // convert bytes to a writable string
	    PrintWriter fout = new PrintWriter(new File("blockChain.bcf"));
	    fout.append(tran+"\r\n");	            // write the transaction to my block chain
	    out.print("Got a transaction\n> ");
	    ++sizeOfBlockChain;
	    fout.close();
	}else if((char)cmd[0]=='b')                 // b for send Block 
	    sendBlock();	
    }
     /**
     * Sends my block chain to the server to be compared to other block chains
     * @author Charles Jackson
     */      
    private static void sendBlock()throws Exception{ 
	OutputStream nout = me.getOutputStream();                // to write messages to the server
	byte[] blockChainBytes = new byte[sizeOfBlockChain * TRANLENGTH]; // to store the block chain to output
     	Scanner fin = new Scanner(new File("blockchain.bcf"));	 // open the file for reading
	for(int i=0; i < sizeOfBlockChain; ++i){		 // for every transaction in the my block chain
	    String tran=fin.nextLine().trim();			 // read one transaction
	    byte[] tranBytes=tran.getBytes();			 // cast the String to bytes
	    for(int j=0; j<TRANLENGTH; ++j)
		blockChainBytes[i*TRANLENGTH+j]=tranBytes[j];
	}
	nout.write(blockChainBytes);				 // send my block chain to the server
	fin.close();
    }
    /**
     * Function prints the menu for Miner User Interface
     * @author Charles Jackson
     */
    private static void printMenu(){
	out.print("start \t- creates your block chain and updates it with the one other miners have.\nclose \t- closes this application\nhelp\t- displays this command guide\n>  ");
    }
    /**
     * Gets the current blockchain from the server (after the server retrieves it from other miners).
     * My current block chain is deleted from existance and is replaced by the correct one that other miners have
     * @author Charles Jackson
     */
    public static void updateBlockChain()throws Exception{
	File blockChain = new File("blockChain.bcf"); // to store my block chain
	PrintWriter fout;
        try{				     
	    blockChain.delete();                      // delete the any old files
	    //blockChain.createNewFile();               // create a new block chain file
	    fout=new PrintWriter(blockChain);
        }catch(Exception ex){
            System.out.print("Failed to update block chain because: "+ex.getMessage() + "\n" + ex.getCause()+"\n> ");
	    return;
        }
	byte[] cmd={(byte)('b')};                     // the command to get a block 
        OutputStream nout = me.getOutputStream();
	nout.write(cmd);	                      // send the command to get a block
        InputStream nin = me.getInputStream();	      // for reading the block from the Server
	if(nin.available() > 0)
	    nin.read(cmd);	                      // read one command character 
	if((char)cmd[0] == 'u'){		      // u for updated. if the updated block has been sent
	    sizeOfBlockChain=0;
	    byte[] tranBytes= new byte[TRANLENGTH];  // for storing one transaction
	    while(nin.available() >= TRANLENGTH){     // read one transaction at a time
		nin.read(tranBytes);		      // read one transaction
		String tran = new String(tranBytes);  // convert the bytes to a string
		fout.append(tran+"\r\n");	      // write the transaction 
		++sizeOfBlockChain;		      // record the new size
	    }					      // move on to the next transaction
	}
	fout.close();
    }
}
