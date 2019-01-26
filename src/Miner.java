import java.net.*;
/**
 * to run on the miners' computers. Remotely stores a log of all transactions
 */
import java.util.*;
import java.io.File;

public class Miner{
    private static Socket me;
    static String menu="";
    Socket me;

    public static void main(String[] args){
        me =new Socket(InetAddress.getLocalHost(), 80);
	Runnable r = new Runnable(){
	    public void run(){
		while(true) receiveTran();
	    }
        };
	Thread receive = new Thread(r);
    }

    /**
     * Receives one transaction from the bank server
     *
     */
    public static void receiveTran(){

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
     *
     *
     */      
    public static void sendBlock(){

    }

    /**
     * Function prints the menufor Miner User Interface
     * @author Mohit Bhole
     */
    public static void printMenu(){
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
