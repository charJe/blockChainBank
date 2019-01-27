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

import java.io.File;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
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

        KeyPair keyPair = Encryption.buildKeyPair();
        // sign the message
        byte [] signed = Encryption.encrypt(keyPair.getPublic(), payerid.getText()+payeeid.getText()+datepick.getValue()+amount.getText());          //payer id + payee id + date + amount

        byte[] pubKey = keyPair.getPublic().getEncoded();
        byte[] privateKey = keyPair.getPrivate().getEncoded();
        File keyss = new File("keys.key");
        PrintWriter fout = new PrintWriter(keyss);
        fout.append(payerid.getText()+privateKey+"\r\n");                                            //PAYER ID IS USED AS THE ENCRYPTION KEY
        fout.close();

        File publickeys = new File("publicKeys.key");
        fout = new PrintWriter(publickeys);
        fout.append(payerid.getText()+pubKey+"\r\n");
        fout.close();

        Main.addToBlockChain(new String(signed));
    }

}
