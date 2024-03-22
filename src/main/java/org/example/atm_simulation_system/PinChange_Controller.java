package org.example.atm_simulation_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javax.security.auth.login.AccountNotFoundException;
import java.awt.*;
import java.io.IOException;

public class PinChange_Controller {

    @FXML
    public Button btn_save;
    @FXML
    public Button btn_cancel;
    @FXML
    public Label new_pin_msg;
    String cardNumber;
    int new_pin;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @FXML
    public TextField new_pin_in;

    @FXML
    public void pin_save(javafx.event.ActionEvent event) {
        String pin_text = new_pin_in.getText();
        new_pin = Integer.parseInt(new_pin_in.getText());

        if (pin_text.isEmpty() || pin_text.length() != 4) {
            new_pin_msg.setText("Please enter a valid pin.");
            return;
        }
        try {
            AccountDetailsFinder.update_new_pin(cardNumber, new_pin);
            new_pin_msg.setText("Pin changed successfully.");

        } catch (NumberFormatException e) {
            new_pin_msg.setText("Invalid input. Please enter valid pin.");
        }
    }

    public void switch_back_home (ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_cancel.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = fxmlLoader.load();
        Home_Controller homeController = fxmlLoader.getController();
        homeController.Display(cardNumber);
        stage.setTitle("ATM Simulation System");
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();

    }
}
