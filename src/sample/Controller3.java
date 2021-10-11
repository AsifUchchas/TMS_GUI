package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.FileReader;

public class Controller3  {

    @FXML
    private ChoiceBox<?> placeList;

    @FXML
    private DatePicker pickDate;

    @FXML
    private TextArea spotDetails;

    @FXML
    private Button Submit;

    @FXML
    void submitRequest(ActionEvent event) {

    }
    
    public static User read() {
        User user = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("tempData.txt"));
            String[] data = br.readLine().split( ", ");
            user.setName(data[0]);
            user.setNid(data[1]);
            user.setPhone(data[2]);
            user.setEmail(data[3]);
            user.setPasswords(null);
            user.setType(data[4]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
