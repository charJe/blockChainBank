package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static java.lang.System.out;

public class Main extends Application {

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
        out.println("Main worked2");
    }


    public static void main(String[] args) {
        out.println("Main worked");
        launch(args);
    }

    /**
     * Sends one transaction to every miner
     * @param tran the encrypted transaction to be sent to the miners
     */
    private static void addToBlockChain(String tran){

    }
    /**
     *
     *
     */
    private static String[][] getBlocks(){
        return (null);
    }
    /**
     *
     *
     */
    private static void sendBlockChainToWeb(){

    }
    /**
     *
     *
     */
    private static void receiveCmds(){

    }
}
