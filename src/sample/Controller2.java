package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller2 {

    @FXML
    private TextField userLoginMail;

    @FXML
    private TextField userLoginPassword;

    @FXML
    private Button Login;

    @FXML
    void userLogin(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample3.fxml"));
        primaryStage.setTitle("Tourist Management System");
        primaryStage.setScene(new Scene(root, 883, 600));
        primaryStage.show();
    }
}