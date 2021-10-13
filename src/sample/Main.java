package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tourist Management System");
        changeScene(primaryStage, "LoginScene.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(Stage primaryStage, String fxmlName) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlName)));
        primaryStage.setScene(new Scene(root, 883, 600));
        primaryStage.show();
    }
}
