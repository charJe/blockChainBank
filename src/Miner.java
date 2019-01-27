import java.net.*;
import java.io.*;
import static java.lang.System.*;
/**
 * to run on the miners' computers. Remotely stores a log of all transactions
 */
import java.util.*;
import java.io.File;

public class Miner{
    private static final int TRANLENGTH=20;
    private static int sizeOfBlockChain;
    private static Socket me;
    private static String menu="";
    
    public static void main(String[] args){
	try{
	    me =new Socket(InetAddress.getLocalHost(), 80);
	}catch(Exception ex){
	    out.println("Could not connect to Server because: "+ex+": "+ex.getMessage());
	}
	Runnable r = new Runnable(){
	    public void run(){
		while(true) receiveTran();
	    }
        };
 	Thread receive = new Thread(r);
	
        printMenu();
        switch(menu){
            case "update":
                updateBlockChain();
                receive.start();
                break;
            case "stop":
                recieve.stop();
                return;
		break;
            case "install":
                updateBlockChain();
		break;
            case "uninstall":
		me.close();
		deleteBlockChain();
		break;
        }
    }
    /**
     * Deletes the my block chain
     * @author Mohit Bhole
     */
    private static void deleteBlockChain(){
	File deleteBlockChain = new File("blockChain.bcf");
        if(deleteBlockChain.delete())
            System.out.println("Files successfully deleted.");
        else
            System.out.println("File not deleted.");	
    }
    /**
     * Receives one transaction from the bank server
     * or, if there server has requsted, send my blockchain to the server
     * @author Charles Jackson
     */
    private static void receiveTran(){
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
	    fout.close();
	}else if((char)cmd[0]=='b')                 // b for send Block 
	    sendBlock();	
    }
     /**
     * Sends my block chain to the server to be compared to other block chains
     * @author Charles Jackson
     */      
    private static void sendBlock(){ 
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
     * @author Mohit Bhole
     */
    private static void printMenu(){
	//TODO mohit fix this function
        while(!(menu.equals("update") || menu.equals("delete") || menu.equals("stop") || menu.equals("install")))
        System.out.println("Menu: ");
        System.out.println("Enter update to enter automatic update mode");
        System.out.println("Enter stop to stop and exit");
        System.out.println("Enter install to install the block-chain (for new computers)");
        System.out.println("Enter delete to delete the block-chain from the computer");
        Scanner input = new Scanner(System.in);
        menu = input.nextLine();
        if(!(menu.equals("update") || menu.equals("delete") || menu.equals("stop") || menu.equals("install"))){
            System.out.println("Invalid input.");
        }
    }
    /**
     * Gets the current blockchain from the server (after the server retrieves it from other miners).
     * My current block chain is deleted from existance and is replaced by the correct one that other miners have
     * @author Charles Jackson
     */
    public static void updateBlockChain(){
	//TODO set sizeOfBlockChain
	File blockChain = new File("blockChain.bcf"); // to store my block chain
	PrintWriter fout;
        try{				     
	    blockChain.delete();                      // delete the any old files
	    blockChain.createNewFile();               // create a new block chain file
	    fout=new PrintWriter(blockChain);
        }catch(Exception ex){
            System.out.println("Failed to update block chain because: "+ex.getMessage() + "\n" + ex.getCause());
	    return;
        }
	byte[] cmd={(byte)('b')};                     // the command to get a block 
        OutputStream nout = me.getOutputStream();
	nout.write(cmd);	                      // send the command to get a block
        InputStream nin = me.getInputStream();	      // for reading the block from the Server
	if(nin.availible() >0)
	    nin.read(cmd);	                      // read one command character 
	if((char)cmd[0] == 'u'){		      // u for updated. if the updated block has been sent
	    sizeOfBlockChain=0;
	    byte[] tranBytes= new bytes[TRANLENGTH];  // for storing one transaction
	    while(nin.availible() >= TRANLENGTH){     // read one transaction at a time
		nin.read(tranBytes);		      // read one transaction
		String tran = new String(tranBytes);  // convert the bytes to a string
		fout.append(tran+"\r\n");	      // write the transaction 
		++sizeOfBlockChain;		      // record the new size
	    }					      // move on to the next transaction
	}
	fout.close();
    }
}
