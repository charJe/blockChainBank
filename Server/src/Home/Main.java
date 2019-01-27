package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.*;
import java.net.*;
import java.io.*;
import static java.lang.System.out;

public class Main extends Application {
    private static int numberOfTrans;
    private static final int TRANLENGTH=128;
    private static final int BLOCKSIZE=5;
    private static final double THRESHOLD=.90;
    private static ServerSocket me;
    private static ArrayList<Socket> miners;
    private static Socket web;    
    
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
	try {
		me = new ServerSocket(80);
	}
	catch(Exception ex){
		return;
	}
	miners = new ArrayList<>();
	Runnable r = new Runnable(){
	    @Override
	    public void run(){
		while(true){
		    try{			
			receiveCmds();
		    }catch(Exception ex){
			out.println("Some problem with getting commands because: "+ex+": "+ex.getMessage());
		    }
		}
	    }
        };
	Runnable c = new Runnable(){
	    @Override
	    public void run(){
		while(true){
		    try{			
			getNewConnections();
		    }catch(Exception ex){
			out.println("Some problem with getting new connections because: "+ex+": "+ex.getMessage());
		    }
		}
	    }
        };
	Thread receiveStuff = new Thread(r);
	Thread getConnections = new Thread(c);
	receiveStuff.start();
	getConnections.start();
        launch(args);
    }

    /**
     * Sends one transaction to every miner
     * @param tran the encrypted transaction to be sent to the miners
     * @author Charles Jackson
     */
    public static void addToBlockChain(String tran)throws Exception{
	++numberOfTrans;
	for(Socket miner: miners){
	    try{
		OutputStream nout = miner.getOutputStream();
		nout.write(tran.getBytes()); // send transaction to miner
	    }catch(SocketTimeoutException tm){
		out.println("miner is missing");
		continue;
	    }catch(Exception ex){
		out.println("Problem adding to block chain because: "+ex+": "+ex.getMessage());
	    }
	}
    }
    /**
     * get the one block chain from every miner
     * @returns String[][] containing block chains from all miners. string[miner number][transaction number]
     * @author Charles Jackson
     */
    private static String[] getBlockChain() throws Exception{										//CHANGED BY MOHIT
        String[][] blockChains = new String[miners.size()][numberOfTrans];
	int i=0;
	for(Socket miner: miners){                        // for every miner
	    OutputStream nout = miner.getOutputStream();
	    byte[] cmd={(byte)('b')};
	    nout.write(cmd);	                          // ask for each miners' block chain
	    InputStream nin = miner.getInputStream();
	    for(int j=0; j<numberOfTrans; ++i){
		byte[] tranBytes = new byte[TRANLENGTH];
		nin.read(tranBytes);                      // read in one transaction 
		blockChains[i][j]=new String(tranBytes);  // store the transaction
	    }
	    ++i;
	}
	String[] trueChain = new String[numberOfTrans];
	String[] trueBlock = new String[BLOCKSIZE];
	for(int tcIndex=0; tcIndex<numberOfTrans; tcIndex+=BLOCKSIZE){
	    double[] percentErrors=new double[miners.size()];
	    for(int tbIndex=tcIndex; tbIndex<BLOCKSIZE; ++tbIndex)
		for(i=0; i < miners.size(); ++i){ // for every miner
		    for(int j=0; j < miners.size(); ++j) // compare to every other miner
			if(i!=j && !blockChains[i][tbIndex].equals(blockChains[j][tbIndex]))
			    ++percentErrors[i];	    
		    percentErrors[i]/=BLOCKSIZE; // average the %error
		}		
	    int t=findMin(percentErrors);
	    double totatlError=0.0;
	    for(i=0; i<percentErrors.length; ++i)
		totatlError+=percentErrors[i];
	    totalError/=(double)percentErrors.length;
	    if(totalError>THRESHOLD) throw new CorruptedBlockException("error > "+THRESHOLD);
	    for(int tbIndex=tcIndex; tbIndex<BLOCKSIZE; ++tbIndex)
		trueChain[tbIndex]=blockChains[t][tbIndex];
        }
	return trueChain;
    }
    /**
     * find the smallest value in the array
     * @param ar the array to be indexed
     * @returns the index of the smallest value
     * @author Charles Jackson
     */
    private static int findMin(double[] ar){
	int min=0;
	for(int i=0; i<ar.length; ++i){
	    if(ar[i] < ar[min])
		min=i;
	}
	return min;
    }
    /**
     * send the true block chain to the web application
     * @author Charles Jackson
     */
    private static void sendBlockChainToWeb()throws Exception{
	if(web==null)throw new SocketException("SocketNotConnected");
	OutputStream wout = web.getOutputStream();
	String[] blockChain=getBlockChain();
	int size=blockChain.length*TRANLENGTH;
	wout.write((""+size).getBytes());
	for(String tran: blockChain)
	    wout.write(tran.getBytes());
    }
    /**
     * receives all commands from all clients
     * b = send me the true block chain; w = i'm the web application; t = i'm sending a single transaction
     * @author Charles Jackson
     */
    private static void receiveCmds()throws Exception{
	byte[] cmd=new byte[1];
	for(int i=0; i <miners.size(); ++i){//for all the miners
	    InputStream nin=miners.get(i).getInputStream();
	    if(nin.available() > 0)
		nin.read(cmd);
	    if((char)cmd[0] == 'b'){
		OutputStream nout = miners.get(i).getOutputStream();
		String[] blockChain=getBlockChain();
		for(String tran: blockChain)
		    nout.write(tran.getBytes());
	    }else if((char)cmd[0] == 'w'){
		web = miners.remove(i); // move the web application from the miners to web
		--i;
	    }
	}
	if(web!=null){ 		// if web is connected
	    InputStream win = web.getInputStream();
	    if(win.available() >= 0)
		win.read(cmd);	// read a command
	    if((byte)cmd[0] == 'b'){
		OutputStream wout = web.getOutputStream();
		String[] blockChain=getBlockChain();
		for(String tran: blockChain)
		    wout.write(tran.getBytes());
	    }
	}
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
		    miners.add(nc);
	    }catch(SocketTimeoutException tm){
		return;		// no more connections
	    }catch(Exception ex){
		out.println("Problem getting new connection because: "+ex+": "+ex.getMessage());
	    }
	}
    }
}
