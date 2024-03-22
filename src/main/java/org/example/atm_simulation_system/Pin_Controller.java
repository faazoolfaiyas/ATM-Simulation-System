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

import java.io.IOException;

public class Pin_Controller {
    String cardNumber;
    int pin;
    int pin_try;
    // objects on pin form
    @FXML
    public TextField pin_in;

    @FXML
    public Label pin_msg;
    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    @FXML
    public Button btn_verify;

    @FXML
    public void verify_pin(ActionEvent event) throws IOException {
        AccountData accountData = AccountDetailsFinder.findDataByCardNumber(cardNumber);
        pin = accountData.getPin();
        pin_try = accountData.getPinTimes();
        if (pin_try < 3){
            if (pin == Integer.parseInt(pin_in.getText())){
                Stage stage = (Stage) btn_verify.getScene().getWindow(); // Get the current stage
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = fxmlLoader.load();
                Home_Controller homeController = fxmlLoader.getController();
                homeController.Display(cardNumber);
                stage.setTitle("ATM Simulation System");
                stage.setScene(new Scene(root, 1000, 600));
                stage.setResizable(false);
                stage.show();
            } else {
                pin_try++;
                AccountDetailsFinder.updatePinTimes(cardNumber, pin_try);
                pin_msg.setText("Wrong pin!");
            }
        } else {
            pin_msg.setText("Your tries are over! Kindly contact the bank...");
        }

    }

    // cancel button on pin form
    @FXML
    public Button btn_cancel;
    @FXML
    public void switch_to_login_cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_cancel.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("ATM Simulation System");
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
    }


}


