package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static java.lang.System.out;

public class Main extends Application {
    private static int numberOfTrans;
    private static final int TRANLENGTH=20;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.getIcons().add(new Image("Home/icons8-paper-money-filled-50.png"));
        primaryStage.setTitle("BlockChain Bank");
        primaryStage.setScene(new Scene(root,1280,720));
        double heightx = primaryStage.getHeight();
        double widthx = primaryStage.getWidth();
        primaryStage.setMinHeight(heightx);
        primaryStage.setMinWidth(widthx);
        primaryStage.show();
    }
    /**MAIN
     */
    public static void main(String[] args){
	numberOfTrans=0;
	Runnable r = new Runnable(){
	    @Override
	    public void run(){
		while(true){
		    receiveCmds();
		}
	    }
        };
        launch(args);
    }

    /**
     * Sends one transaction to every miner
     * @param tran the encrypted transaction to be sent to the miners
     * @author Charles Jackson
     */
    private static void addToBlockChain(String tran)throws Exception{
	++numberOfTrans;
	for(Socket miner: miners){
	    try{
		OutputStream nout = miner.getOutputStream();
		nout.write(tran.getBytes());
	    }catch(SocketTimeoutException tm){
		out.println("miner is missing");
		continue;
	    }catch(Exception ex){
		out.println("Problem adding to block chain because: "+ex+": "+ex.getMessage();
	    }
	}
    }
    /**
     * get the one block chain from every miner
     * @returns String[][] containing block chains from all miners. string[miner number][transaction number]
     * 
     */
    private static String[][] getBlockChains(){
        String[][] blockChains = new String[miners.size()][numberOfTrans];
	int i=0;
	for(Socket miner: miners){
	    OutputStream nout = miner.getOutputStream();
	    byte[] cmd={(byte)('b')};
	    nout.write(cmd);	                          // ask for each miners' block chain
	    InputStream nin = miner.getInputStream();
	    for(int j=0; j<numberOfTrans; ++i){
		byte[] tranBytes = new bytes[TRANLENGTH];
		nin.read(tranBytes);                      // read in one transaction 
		blockChains[i][j]=new String(tranBytes);
	    }
	    ++i;
	}
    }
    /**
     * 
     *
     */
    private static void sendBlockChainToWeb(){

    }
    /**
     * receives one command from one client
     *
     */
    private static void receiveCmdss(){
	
    }
    /**
     * accepts all the pending connections from new miners or from the website
     * @author Charles Jackson
     */
    private static void getNewConnections(){
	while(true){
	    try{
		Socket nc = me.accept();
		if(nc!=null)
		    miners.addFirst(nc);
	    }catch(SocketTimeoutException tm){
		return;		// no more connections
	    }catch(Exception ex){
		out.println("Problem getting new connection because: "+ex+": "+ex.getMessage());
	    }
	}
    }
}
