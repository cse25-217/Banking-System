
package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OpenAccountController {

    @FXML private ComboBox<String> accountTypeComboBox;
    @FXML private TextField initialDepositField;
    @FXML private TextField branchField;
    @FXML private CheckBox employedCheckBox;
    @FXML private TextField employerNameField;
    @FXML private TextField employerAddressField;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        accountTypeComboBox.getItems().addAll(
            "Savings Account",
            "Investment Account",
            "Cheque Account"
        );
    }

    @FXML
    private void handleEmploymentChange() {
        boolean employed = employedCheckBox.isSelected();

        employerNameField.setDisable(!employed);
        employerAddressField.setDisable(!employed);

        if (!employed) {
            employerNameField.clear();
            employerAddressField.clear();
        }
    }
    @FXML
private void handleBackToDashboard(ActionEvent event) {
    Navigation.goToDashboard(event);
}
    @FXML
    private void handleOpenAccount() {
        String type = accountTypeComboBox.getValue();
        String depositText = initialDepositField.getText().trim();
        String branch = branchField.getText().trim();

        if (type == null || depositText.isEmpty() || branch.isEmpty()) {
            messageLabel.setText("Please complete all required fields.");
            return;
        }

        double deposit;

        try {
            deposit = Double.parseDouble(depositText);
        } catch (NumberFormatException e) {
            messageLabel.setText("Enter a valid deposit amount.");
            return;
        }

        if (deposit <= 0) {
            messageLabel.setText("Deposit must be greater than zero.");
            return;
        }

        if (type.equals("Investment Account") && deposit < 500) {
            messageLabel.setText(
                "Investment accounts require at least BWP 500."
            );
            return;
        }

        if (type.equals("Cheque Account")) {
            if (!employedCheckBox.isSelected()) {
                messageLabel.setText(
                    "Only employed customers can open cheque accounts."
                );
                return;
            }

            if (employerNameField.getText().trim().isEmpty()
                    || employerAddressField.getText().trim().isEmpty()) {
                messageLabel.setText(
                    "Enter your employer name and address."
                );
                return;
            }
        }

        messageLabel.setText("Details validated successfully.");
        
        // Account creation and database saving will be connected next.
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) accountTypeComboBox
                .getScene().getWindow();
        stage.close();
    }
}
