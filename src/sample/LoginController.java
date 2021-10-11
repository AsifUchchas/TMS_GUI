package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    @FXML
    private TextField userLoginMail;

    @FXML
    private TextField userLoginPassword;

    @FXML
    private Button Login;

    @FXML
    private Label errorMsg;

    @FXML
    void linkRegister(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Main.changeScene(stage, "RegistrationScene.fxml");
    }

    @FXML
    void userLogin(ActionEvent event) throws Exception {

        List<String> cred = new ArrayList<>();
        cred.add("Login");
        cred.add(userLoginMail.getText());
        cred.add(userLoginPassword.getText());


        Socket sc = new Socket("localhost", 6600);
        OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
        BufferedWriter sendStr = new BufferedWriter(o);

        InputStream inputStream = sc.getInputStream();
        ObjectInputStream receiveObj = new ObjectInputStream(inputStream);

        OutputStream oo = sc.getOutputStream();
        ObjectOutputStream sendObj = new ObjectOutputStream(oo);

        InputStreamReader isr = new InputStreamReader(sc.getInputStream());
        BufferedReader receiveStr = new BufferedReader(isr);

        // sending credentials
        sendObj.writeObject(cred);


        // reading response
        String response = receiveStr.readLine();
        if (response.equals("SUCCESS!\n")) {
            List<String> info = (List<String>) receiveObj.readObject();
            User user = new User(info.get(1), info.get(2), info.get(3), info.get(4), info.get(5), info.get(6));

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Main.changeScene(stage, "sample3.fxml");
        }
        else {
            errorMsg.setText("Login Failed!");
        }

    }
}
