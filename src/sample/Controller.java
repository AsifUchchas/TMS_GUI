package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField userName;

    @FXML
    private TextField userNID;

    @FXML
    private TextField Phone;

    @FXML
    private TextField userMail;

    @FXML
    private TextField userPassword;

    @FXML
    private Button Register;

    @FXML
    void userLogin(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
        primaryStage.setTitle("Tourist Management System");
        primaryStage.setScene(new Scene(root, 883, 600));
        primaryStage.show();
    }
}
