package org.example.atm_simulation_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class Login_Controller {


    @FXML
    public TextField card_in;
    @FXML
    public DatePicker date_in;
    @FXML
    public TextField cvv_in;
    @FXML
    public Label error_msg;

    // enter button on login form
    @FXML
    public Button btn_enter;

    @FXML
    public void enter(ActionEvent event) {
        String cardNumberText = card_in.getText();
        String cvvText = cvv_in.getText();

        if (cardNumberText.isEmpty() || cardNumberText.length() != 16) {
            error_msg.setText("Please enter a valid 12-digit account number.");
            return;
        }
        LocalDate date = date_in.getValue();
        if (date == null) {
            error_msg.setText("Please enter a Valid/Thru.");
            return;
        }
        String dateStr = date_in.toString();


        if (cvvText.isEmpty() || cvvText.length() != 3) {
            error_msg.setText("Please enter a valid 3-digit CVV number.");
            return;
        }

        try {
            String cardNumber = cardNumberText;
            int cvvNumber = Integer.parseInt(cvvText);
            // Call the method from AccountDetailsFinder class to find account data by card number
            AccountData accountData = AccountDetailsFinder.findDataByCardNumber(cardNumber);

            if (cvvNumber == accountData.getCvv()) {
                Stage stage = (Stage) btn_enter.getScene().getWindow(); // Get the current stage
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pin.fxml"));
                Parent root = fxmlLoader.load();
                Pin_Controller pin_controller = fxmlLoader.getController();
                pin_controller.setCardNumber(cardNumber);
                stage.setTitle("ATM Simulation System");
                stage.setScene(new Scene(root, 1000, 600));
                stage.setResizable(false);
                stage.show();

            } else {
                error_msg.setText("Card details are not matched!");
            }
            /*
            // Check if account data was found and print it
            if (accountData != null) {
                System.out.println("Owner Name: " + accountData.getOwnerName());
                System.out.println("Account Number: " + accountData.getAccountNumber());
                System.out.println("Amount: " + accountData.getAmount());
                System.out.println("CVV: " + accountData.getCvv());
                System.out.println("PIN: " + accountData.getPin());
                System.out.println("PIN Times: " + (accountData.getPinTimes() != null ? accountData.getPinTimes() : "Not available"));
            } else {
                System.out.println("Account not found.");
            }
            */


        } catch (NumberFormatException e) {
            error_msg.setText("Invalid input. Please enter valid numbers.");
        } catch (IOException e) {
            error_msg.setText("Error switching to PIN screen.");
            System.out.println(e);
        } catch (NullPointerException e) {
            error_msg.setText("No account found!");
        }
    }

    // error on switching to pin
}


// how to call the get method in verify action
