package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
class PlacePopulation{
    String place;
    String population;

    public PlacePopulation(String place, String population) {
        this.place = place;
        this.population = population;
    }
}
class placeDatePopulation{
    String placeName;
    int PeopleAmount;
    LocalDate date;

    public placeDatePopulation(String placeName, int peopleAmount, LocalDate date) {
        this.placeName = placeName;
        PeopleAmount = peopleAmount;
        this.date = date;
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
    private Label result;
    @FXML
    private Button Submit;

    private String[] places={"Cox's Bazar","Saint martin","Sajek","Bandarban"};
    ArrayList<placeDatePopulation> pdp = new ArrayList<>();
    @FXML
    void submitRequest(ActionEvent event) {

            String place = placeList.getValue();  // getting the value of the choosen place
            LocalDate tourDate = pickDate.getValue(); // getting the date

            for (placeDatePopulation p :pdp){
                if(place.equals(p.placeName)){
                    if(tourDate.compareTo(p.date) == 0 && p.PeopleAmount>0) {
                        p.PeopleAmount--;
                        result.setText("   Congratulations");
                        break;
                    }else {
                        result.setText("Please choose another date. Thanks");
                    }
                }
            }
            spotDetails.clear();
            spotDetails.appendText("Spot name    "+"Date   "+"   Available slots"+"\n");
            for (placeDatePopulation p : pdp){
                spotDetails.appendText("\n"+p.placeName+"   "+p.date+"    "+p.PeopleAmount);
            }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeList.getItems().addAll(places); // inserting place names to the choice boxs
        ArrayList<PlacePopulation> pp =new ArrayList<>();
        try {
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
        spotDetails.appendText("Spot name    "+"Date   "+"   Available slots"+"\n");
        for (PlacePopulation p : pp){
            int count = 0;
            LocalDate currentDate1 =  LocalDate.now();
            while (true){
                LocalDate currentDatePlus = currentDate1.plusDays(count);
                int totalPeople = Integer.parseInt(p.population);
                spotDetails.appendText("\n"+p.place+"   "+currentDatePlus+"    "+totalPeople/5);
                pdp.add(new placeDatePopulation(p.place,totalPeople/5,currentDatePlus));
                count+=3;
                if(count>15) break;
            }

        }
    }
}
