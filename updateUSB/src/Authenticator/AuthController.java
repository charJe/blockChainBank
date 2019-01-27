package Authenticator;

import javafx.fxml.FXML;
import java.util.*;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

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

    @FXML
    private TextField driveText;



    @FXML
    void exitForm(ActionEvent event) {
        Stage stage = (Stage) exitbtn.getScene().getWindow();
        stage.close();
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
    void authenticate(ActionEvent event) throws Exception{
        String path = driveText.getText();

        path = path + ":\\BankBlockChain\\personalprivatekey.key";
        File privKey = new File(path);
        System.out.println("working");
        privKey.setReadable(true);
        Scanner input = new Scanner(privKey);
        String privKeyLine = input.nextLine();
        Scanner search = new Scanner(new File("keys.key"));

        boolean authenticated=false;
        while(search.hasNext())
        {
            String allKeysLine = search.nextLine();
            if(allKeysLine.equals(privKeyLine))
                authenticated=true;
        }
        if(authenticated){
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
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
        //line.substring(0,11) is the account id, and line.substring(12) is the private key.

    }

}
