package org.example.atm_simulation_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Home_Controller {

    String cardNumber;
    String account_number;
    String owner;
    double amount;
    @FXML
    public Label account_details;

    public void Display(String cardNumber) {
        this.cardNumber = cardNumber;
        AccountData accountData = AccountDetailsFinder.findDataByCardNumber(cardNumber);
        account_number = accountData.getAccountNumber();
        owner = accountData.getOwnerName();
        amount = accountData.getAmount();
        account_details.setText("Owner: " + owner + "\n\nAccount number: " + account_number + "\n\nBalance: " + amount + "LKR.");
    }

    @FXML
    public Button btn_change;
    @FXML
    public void pin_change (ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_change.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PinChange.fxml"));
        Parent root = fxmlLoader.load();
        PinChange_Controller pinChangeController = fxmlLoader.getController();
        pinChangeController.setCardNumber(cardNumber);
        stage.setTitle("ATM Simulation System");
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public Button btn_withdraw;
    @FXML
    public void switch_to_withdraw (ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_withdraw.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Withdrawal.fxml"));
        Parent root = fxmlLoader.load();
        Withdrawal_Controller withdrawalController = fxmlLoader.getController();
        withdrawalController.setCardNumber(cardNumber);
        stage.setTitle("ATM Simulation System");
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
    }

    // exit button on home form
    @FXML
    public Button btn_exit;

    @FXML
    public void switch_to_login_exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_exit.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("ATM Simulation System");
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
    }


}

