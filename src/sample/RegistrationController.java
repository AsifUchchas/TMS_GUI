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

public class RegistrationController {

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
    private Label errorMsg;

    @FXML
    void userRegister(ActionEvent event) throws Exception {
        int flag = 0;
        String data;

        // Username length
        if (userName.getText().length() < 2)
            flag = 1;

        // NID Length (Just guess)
        if (userNID.getText().length() < 5 && flag == 0)
            flag = 2;

        // Phone Validation
        if ((Phone.getText().length() < 5 || !Utils.isValidPhone(Phone.getText())) && flag == 0)
            flag = 3;

        // Email Validation
        data = userMail.getText();
        if (!Utils.isValidEmail(data) && flag == 0)
            flag = 4;

        // Passwords Validation
        data = userPassword.getText();
        if (!Utils.isValidPass(data) && flag == 0)
            flag = 5;

        // if everything valid
        if (flag == 0) {
            List<String> info = new ArrayList<>();
            info.add("Registration");
            info.add(userName.getText());
            info.add(userNID.getText());
            info.add(userMail.getText());
            info.add(userPassword.getText());
            info.add(Phone.getText());
            info.add("User");
            if (sendData(info)) {
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Main.changeScene(stage, "RegSuccessScene.fxml");
            }
            else {
                errorMsg.setText("USER ALREADY EXIST!");
            }
        }
        else {
            if(flag == 1)
                errorMsg.setText("INVALID USER NAME!");
            else if(flag == 2)
                errorMsg.setText("INVALID NID!");
            else if(flag == 3)
                errorMsg.setText("INVALID PHONE NUMBER!");
            else if(flag == 4)
                errorMsg.setText("INVALID EMAIL!");
            else if(flag == 5)
                errorMsg.setText("INVALID PASSWORDS!");
            else
                errorMsg.setText("Something is wrong!");
        }
    }

    boolean sendData(List<String> list){
        try {
            Socket sc = new Socket("localhost", 6600);

            OutputStream o = sc.getOutputStream();
            ObjectOutputStream send = new ObjectOutputStream(o);

            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            BufferedReader cReader = new BufferedReader(isr);

            send.writeObject(list);

            String res = cReader.readLine();
//            System.out.println(res);  // debug
            sc.close();
            if (res.contains("FAILED!"))
                return false;
            return true;
        }
        catch (IOException e){
            errorMsg.setText("Something is wrong!");
        }
        return false;
    }
}
