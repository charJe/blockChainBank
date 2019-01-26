import java.net.*;
import static java.lang.System.*;
/**
 * to run on the miners' computers. Remotely stores a log of all transactions
 */
import java.util.*;
import java.io.File;

public class Miner{
    private static final TRANLENGTH=20;
    private static Socket me;
    private static String menu="";
    private static PrintWriter fout;
    
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
                recieve.start();
                break;
            case "stop":
                recieve.stop();
                return;
            case "install":
                File blockChain = new File("blockChain.bcf");
		
                try {
                    blockChain.createNewFile();
		    myBlockChain=new PrintWriter(blockBhain);
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage() + "\n" + ex.getCause());
                }
                updateBlockChain();
            case "delete":
                File deleteBlockChain = new File("blockChain.bcf");
                if(deleteBlockChain.delete()){
                    System.out.println("Files successfully deleted.");
                }
                else{
                    System.out.println("File not deleted.");
                }
        }
    }

    /**
     * Receives one transaction from the bank server
     * @author Charles Jackson
     */
    private static void receiveTran(){
	InputStream nin=me.getIntputStream(); // to read messages sent from the server
	byte[] cmd=new byte[1];		      // store the command from the server
	if(nin.available() > 0)	              // if there is a command from the server
	    nin.read(cmd);		      // read the command character
	if((char)cmd[0]=='t'){		      // t for receive Transaction. this receives one block
	    byte[] tranBytes =new byte[];     // to store the new transaction
	    nin.read(tranBytes);	      // read the new transaction
	    String tran=new String(tranBytes);// convert bytes to a writable string
	    fout.append(tran+"\r\n");	      // write the transaction to my block chain
	}else if((char)cmd[0]=='b')           // b for send Block 
	    sendBlock();	
    }

     /**
     *
     *
     */      
    private static void sendBlock(){ 

    }

    /**
     * Function prints the menufor Miner User Interface
     * @author Mohit Bhole
     */
    private static void printMenu(){
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
     *
     *
     */
    public static void updateBlockChain(){

    }

}
