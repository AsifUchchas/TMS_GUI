package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField userLoginMail;

    @FXML
    private TextField userLoginPassword;

    @FXML
    private Button Login;

    @FXML
    void linkRegister(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Main.changeScene(stage, "RegistrationScene.fxml");
    }

    @FXML
    void userLogin(ActionEvent event) {

    }
}
