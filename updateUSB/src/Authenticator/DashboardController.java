package Authenticator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class DashboardController {

    @FXML
    private Text text3;

    @FXML
    private Button exitbtn;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text nameField;

    @FXML
    private AnchorPane flashDrivePane;

    @FXML
    private void updateTrans(){
        text1.setText("x");
        text2.setText("y");
        text3.setText("z");
        nameField.setText("Jacob Hill");
    }


}
