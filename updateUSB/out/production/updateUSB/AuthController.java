package UpdateUSB;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {

    @FXML
    private Text acid;

    @FXML
    private Button authbtn;

    @FXML
    private Button exitbtn;

    @FXML
    private AnchorPane flashDrivePane;

    @FXML
    private ImageView usbimage;

    @FXML
    private Button privbtn;

    @FXML
    private Button submitbtn;

    private Stage stage;

    @FXML
    void exitForm(ActionEvent event) {

    }

    @FXML
    void loadPriv(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateUSB.fxml"));
            Stage stage = (Stage) authbtn.getScene().getWindow();
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
    void authenticate(ActionEvent event) {

    }

}
