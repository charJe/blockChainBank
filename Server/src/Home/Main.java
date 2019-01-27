package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static java.lang.System.out;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("Bank");
        primaryStage.setScene(new Scene(root));
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
