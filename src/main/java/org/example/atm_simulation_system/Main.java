package org.example.atm_simulation_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the icon image
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

        // Set the icon image to the stage
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.setTitle("ATM Simulation System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
