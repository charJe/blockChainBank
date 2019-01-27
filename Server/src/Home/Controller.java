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
import static java.lang.System.*;
public class Controller{

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
            clearForm(new ActionEvent());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlashDrive.fxml"));
            Stage stage = (Stage) usbbtn.getScene().getWindow();
            double heightx = stage.getHeight();
            double widthx = stage.getWidth();
            Scene scene = new Scene(loader.load(),widthx,heightx);
            stage.setMinHeight(heightx);
            stage.setMinWidth(widthx);
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    @FXML
    private void exitForm(ActionEvent event) throws Exception{
        Stage stage = (Stage) transbtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clearForm(ActionEvent event) throws Exception{
        amount.clear();
        payeeid.clear();
        payerid.clear();
        datepick.setValue(null);
    }

    @FXML
    private void submitTransaction(ActionEvent event) throws Exception{

    }

}
