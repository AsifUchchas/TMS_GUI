package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
class PlacePopulation{
   private String place;
   private String population;

    public PlacePopulation(String place, String population) {
        this.place = place;
        this.population = population;
    }
}
public class Controller3 implements Initializable {
    private BufferedReader cReader;
    private BufferedWriter cWriter;
    public Controller3() {
        Socket sc = null;
        try {
            sc = new Socket("localhost", 6600);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            cWriter = new BufferedWriter(o);
            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            cReader = new BufferedReader(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ChoiceBox<String> placeList;

    @FXML
    private DatePicker pickDate;

    @FXML
    private TextArea spotDetails;

    @FXML
    private Button Submit;

    private String[] places={"Cox's Bazar","Saint martin","Sajek","Bandarban"};

    @FXML
    void submitRequest(ActionEvent event) {
        String place = placeList.getValue();  // getting the value of the choosen place
        //System.out.println(place);
        LocalDate tourDate = pickDate.getValue(); // getting the date
        String formattedDate = tourDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        //System.out.println(formattedDate);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeList.getItems().addAll(places); // inserting place names to the choice boxs
        try {
            ArrayList<PlacePopulation> pp =new ArrayList<>();
            // reading the place names and allowed tourist population of it
            while (true){
                String placeName=cReader.readLine();
                if (placeName.equals("Null"))
                    break;
                String poputation=cReader.readLine();
                pp.add(new PlacePopulation(placeName,poputation));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDate currentdDate1 =  LocalDate.now();
        LocalDate currentDatePlus1 = currentdDate1.plusDays(23);
        System.out.println(currentDatePlus1);
    }
}
