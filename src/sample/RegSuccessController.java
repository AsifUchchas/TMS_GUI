package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class RegSuccessController {


        @FXML
        void ButtonClicked(ActionEvent event) throws Exception {
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Main.changeScene(stage, "LoginScene.fxml");
        }
}
