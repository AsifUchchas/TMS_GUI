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
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        User admin = new User(null, null, "root@email.com", "Abcd1234@", null, "Admin");
        List<String> cred = new ArrayList<>();
        cred.add("Login");
        cred.add(userLoginMail.getText());
        cred.add(userLoginPassword.getText());
        
        if (cred.get(1).equals(admin.getEmail()) && cred.get(2).equals(admin.getPasswords()))
        {
            System.out.println(" - Admin user detected");
            System.out.println(" - Logging in to Admin Control Panel");
            Main.changeScene(stage, "AdminPanelScene.fxml");
        }
        else {
            System.out.println(" - Connecting to server");
            Socket sc = new Socket("localhost", 6600);

            OutputStream oo = sc.getOutputStream();
            ObjectOutputStream sendObj = new ObjectOutputStream(oo);

            InputStream inputStream = sc.getInputStream();
            ObjectInputStream receiveObj = new ObjectInputStream(inputStream);

            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            BufferedWriter sendStr = new BufferedWriter(o);

            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            BufferedReader receiveStr = new BufferedReader(isr);

            // sending credentials
            System.out.println(" - Sending credentials");
            sendObj.writeObject(cred);

            // reading response
            String response = receiveStr.readLine();
            System.out.println(" - Received " + response + " response");
            if (response.equals("SUCCESS!")) {
                List<String> info = (List<String>) receiveObj.readObject();
                System.out.println(" - Received logged user info from server");
                User user = new User(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5));
                System.out.println(" - Saving user info for later use");
                write(user);    // writing info to a temp file
                System.out.println(" - Logging in to User Control Panel");
                Main.changeScene(stage, "sample3.fxml");
            } else {
                errorMsg.setText("Login Failed!");
            }
        }
    }

    public static void write(User user) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tempData.txt"));

            bw.write(user.getName() + ", ");
            bw.write(user.getNid() + ", ");
            bw.write(user.getEmail() + ", ");
            bw.write(user.getPhone() + ", ");
            bw.write(user.getType() + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
