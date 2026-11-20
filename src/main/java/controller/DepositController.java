
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepositController {

    @FXML private ComboBox<String> accountComboBox;
    @FXML private TextField accountNumberField;
    @FXML private TextField amountField;
    @FXML private TextField referenceField;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        // Customer accounts will be loaded from the database here.
        accountComboBox.setOnAction(event -> {
            String selectedAccount = accountComboBox.getValue();

            if (selectedAccount != null) {
                accountNumberField.setText(selectedAccount);
            }
        });
    }

    @FXML
    private void handleConfirmDeposit() {
        String accountNumber = accountNumberField.getText().trim();
        String amountText = amountField.getText().trim();
        String reference = referenceField.getText().trim();

        if (accountComboBox.getValue() == null || accountNumber.isEmpty()) {
            messageLabel.setText("Please select an account.");
            return;
        }

        if (amountText.isEmpty()) {
            messageLabel.setText("Please enter a deposit amount.");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            messageLabel.setText("Enter a valid amount.");
            return;
        }

        if (amount <= 0) {
            messageLabel.setText("Deposit amount must be greater than zero.");
            return;
        }

        messageLabel.setText("Deposit details validated successfully.");

        // Actual balance update and transaction saving
        // will be connected to the model and database next.
    }
    @FXML
private void handleBackToDashboard(ActionEvent event) {
    Navigation.goToDashboard(event);
}
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) accountComboBox.getScene().getWindow();
        stage.close();
    }
}