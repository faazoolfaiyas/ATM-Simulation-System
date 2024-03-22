package org.example.atm_simulation_system;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.awt.*;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Withdrawal_Controller {

    @FXML
    public Button btn_withdraw;
    @FXML
    public Label withdraw_msg;
    @FXML
    public TextField amount_in;
    @FXML
    public Button btn_cancel;
    String cardNumber;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @FXML
    public void perform_withdraw (ActionEvent event) {
        AccountData accountData = AccountDetailsFinder.findDataByCardNumber(cardNumber);
        String account_number = accountData.getAccountNumber();
        double balance = accountData.getAmount();
        try{
            double amount = Double.parseDouble(amount_in.getText());
            if (amount < balance){
                double new_balance = balance - amount;
                AccountDetailsFinder.update_amount(account_number, new_balance);
                withdraw_msg.setText("Withdrawal success.\nAmount = " + amount + "LKR.");

            } else {
                withdraw_msg.setText("Amount is higher than your balance.");
            }
        } catch (NumberFormatException e){
            withdraw_msg.setText("Invalid input. Please enter an amount.");
        } catch (Exception e){
            withdraw_msg.setText("Something went wrong.");
        }


    }

    @FXML
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
