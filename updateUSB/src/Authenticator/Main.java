package Authenticator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import static java.lang.System.*;

public class Main extends Application {
    private static int TRANLENGTH=128;
    private static Socket me;
        
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        Parent root = FXMLLoader.load(getClass().getResource("Authentic.fxml"));
        primaryStage.getIcons().add(new Image("icons8-paper-money-filled-50.png"));
        primaryStage.setTitle("BlockChain Bank");
        primaryStage.setScene(new Scene(root,1280,720));
        double heightx = primaryStage.getHeight();
        double widthx = primaryStage.getWidth();
        primaryStage.setMinHeight(heightx);
        primaryStage.setMinWidth(widthx);
        primaryStage.show();
    }

    /**MAIN 
     * Establishes connection to server
     * @author Charles Jackson
     */
    public static void main(String[] args) {
	out.print("Conneting to Bank Server...");
        try{
	    me = new Socket(InetAddress.getByName("10.204.88.6"), 80); // connect to main server
	    out.println("Got Connection!");
	    byte[] cmd={(char)('w')};
	    OutputStream wout=me.getOutputStream();
	    wout.write(cmd);	// tells the server that I am the web application and not a miner
	}catch(Exception ex){
	    out.println("Could not connect to server because: "+ ex+": "+ex.getMessage());
	}
        launch(args);
    }
    /**
     * calculates what the true current block is based on active miners
     * @return a list of transactions on the block chain
     * @author Charles Jackson
     */
    public static String[] getBlockChain()throws Exception{
	byte[] cmd={(char)('b')}; // b for send the block chain to me
	OutputStream wout=me.getOutputStream();
	wout.write(cmd);	// ask for the current block chain
	InputStream win = me.getInputStream();
	byte[] sizeBytes = new byte[2];
	win.read(sizeBytes);		// the number of bytes that the block chain takes up
	String nsize=new String(sizeBytes);
	int size=Integer.parseInt(nsize); // still the byte size
	byte[] blockChainBytes = new byte[size];
	while(win.available() >= 0)
	    win.read(blockChainBytes); // read the block chain
	String[] blockChain=new String[size/TRANLENGTH];
	for(int i=0,j=0; j<size; ++j){
	    if(j % TRANLENGTH ==0) ++i;
	    blockChain[i]+=blockChainBytes[j];
	}
	return blockChain;
    }
}
