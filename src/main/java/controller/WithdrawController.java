
package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawController {

    @FXML private ComboBox<String> accountComboBox;
    @FXML private TextField accountNumberField;
    @FXML private TextField amountField;
    @FXML private TextField referenceField;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        // Only Investment and Cheque accounts will be loaded here.
        accountComboBox.setOnAction(event -> {
            String selectedAccount = accountComboBox.getValue();

            if (selectedAccount != null) {
                accountNumberField.setText(selectedAccount);
            }
        });
    }

    @FXML
    private void handleConfirmWithdrawal() {

        String accountNumber = accountNumberField.getText().trim();
        String amountText = amountField.getText().trim();

        if (accountComboBox.getValue() == null || accountNumber.isEmpty()) {
            messageLabel.setText("Please select an eligible account.");
            return;
        }

        if (amountText.isEmpty()) {
            messageLabel.setText("Please enter the withdrawal amount.");
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
            messageLabel.setText(
                "Withdrawal amount must be greater than zero."
            );
            return;
        }

        messageLabel.setText(
            "Withdrawal details validated successfully."
        );

        // Actual withdrawal, balance checking and transaction
        // recording will be connected to the model and database.
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
