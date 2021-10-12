package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class AdminPanelController {

    @FXML
    private TextField placeName;

    @FXML
    private TextField numberOfPeople;

    @FXML
    void manipulateInformation(ActionEvent event) {
        HashMap<String, Integer> place = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/sample/place_population"));
            String line1;
            String line2;
            while ((line1 = br.readLine()) != null) {
                line2 = br.readLine();
                place.put(line1, Integer.parseInt(line2));
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        place.put(placeName.getText(), Integer.parseInt(numberOfPeople.getText()));

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/sample/place_population"));
            for (String key : place.keySet()) {
                bw.write(key + "\n");
                bw.write(place.get(key) + "\n");
            }
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

