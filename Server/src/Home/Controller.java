package Home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private Button usbbtn;

    @FXML
    private TextField amount;

    @FXML
    private VBox colotvbox;

    @FXML
    private AnchorPane homepane;

    @FXML
    private VBox box1;

    @FXML
    private DatePicker datepick;

    @FXML
    private TextField payerid;

    @FXML
    private Button cancelbtn;

    @FXML
    private TextField payeeid;

    @FXML
    private Button transbtn;

    @FXML
    private Button submitbtn;

    @FXML
    private void loadFlashDrive(ActionEvent event) throws Exception{
        AnchorPane pane2 = FXMLLoader.load(getClass().getResource("FlashDriveController.fxml"));
        pane2.getChildren().setAll(pane2);
        System.out.println("flash");
    }

    @FXML
    private void loadHome(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        pane.getChildren().setAll(pane);
    }
    public static void main(String[] args){
	out.println("Main worked");
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

    }
    /**
     *
     *
     */
    private static sendBlockChainToWeb(){

    }
    /**
     *
     *
     */
    private static receiveCmds(){

    }    
}
