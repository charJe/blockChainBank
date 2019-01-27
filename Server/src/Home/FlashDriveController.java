package Home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
    private void loadHome(ActionEvent event) throws Exception {
        try {
            clearForm(new ActionEvent());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) transbtn.getScene().getWindow();
            double heightx = stage.getHeight();
            double widthx = stage.getWidth();
            Scene scene = new Scene(loader.load(), widthx, heightx);
            stage.setMinHeight(heightx);
            stage.setMinWidth(widthx);
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    private void exitForm(ActionEvent event) throws Exception {
        Stage stage = (Stage) transbtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clearForm(ActionEvent event) throws Exception {
        passfield.clear();
        acidfield.clear();
    }

    @FXML
    private void writeToFlashDrive(ActionEvent event) throws Exception {

    }


    /**
     *
     * @param event
     * @throws Exception
     * Author Mohit Bhole
     */
    @FXML
    private void usbSaveKeys(ActionEvent event) throws Exception{

        System.out.println("Enter the path of the USB Drive(Just the letter): ");
        Scanner consolein = new Scanner(System.in);
        String path = consolein.nextLine();

        path = path + ":\\BankBlockChain\\";

        File keyfile = new File(path);
        keyfile.createNewFile();
        PrintWriter fout = new PrintWriter(keyfile);
        String acid = acidfield.getText();
        Scanner in = new Scanner("keys.txt");

        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.substring(0,11).equals(acid))   {
                fout.write(line.substring(12));
                break;
            }
        }
        keyfile.setReadable(false);
        keyfile.setExecutable(false);
        keyfile.setWritable(false);

        in.close();
        fout.close();
    }
}