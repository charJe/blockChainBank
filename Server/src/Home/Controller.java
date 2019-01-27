package Home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlashDrive.fxml"));
            Stage stage = (Stage) usbbtn.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
