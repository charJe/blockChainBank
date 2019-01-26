import java.net.*;
/**
 * to run on the miners' computers. Remotely stores a log of all transactions
 */
public class Miner{
    private static Socket me;

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
	
    }

     /**
     * 
     * 
     */      
    public static void sendBlock(){
	
    }
     /**
     * 
     *  
     */      
    public static void updateBlockChain(){
	
    }
    
}
