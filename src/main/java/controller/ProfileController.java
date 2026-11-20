
package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class ProfileController {

    @FXML private TextField customerIdField;
    @FXML private TextField firstNameField;
    @FXML private TextField surnameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField emailField;

    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        // Customer profile details will be loaded from the database.
    }

    @FXML
    private void handleUpdateProfile() {

        String firstName = firstNameField.getText().trim();
        String surname = surnameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneNumberField.getText().trim();
        String email = emailField.getText().trim();

        if (firstName.isEmpty() || surname.isEmpty()
                || address.isEmpty() || phone.isEmpty()
                || email.isEmpty()) {

            messageLabel.setText("Please complete all fields.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            messageLabel.setText("Please enter a valid email address.");
            return;
        }

        if (!phone.matches("[0-9+ -]{7,20}")) {
            messageLabel.setText("Please enter a valid phone number.");
            return;
        }

        messageLabel.setText(
            "Profile details validated successfully."
        );

        // Updated details will be saved to the database during JDBC integration.
    }
    
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) customerIdField.getScene().getWindow();
        stage.close();
    }
}
