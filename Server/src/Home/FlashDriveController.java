package Home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class FlashDriveController {

    @FXML
    private Button usbbtn;

    @FXML
    private TextField passfield;

    @FXML
    private ImageView transimg;

    @FXML
    private Button cancelbtn;

    @FXML
    private AnchorPane flashDrivePane;

    @FXML
    private VBox colorbox;

    @FXML
    private Button transbtn;

    @FXML
    private ImageView usbimage;

    @FXML
    private Button submitbtn;

    @FXML
    private TextField acidfield;

    @FXML
    private void loadHome(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        pane.getChildren().setAll(pane);
        System.out.println("home");
    }
}