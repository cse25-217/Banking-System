package controller;
import dao.CustomerDAO;
import model.Customer;
import util.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
private void handleLogin() {

    String username = usernameField.getText().trim();
    String password = passwordField.getText();

    if (username.equals("customer") && password.equals("12345")) {

        try {Customer customer = new CustomerDAO().findById("CUST001");
            Session.setCurrentCustomer(customer); 
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/Dashboard.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();

            stage.setTitle("NOVA BANK - Dashboard");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();

        } catch (Exception e) {
            messageLabel.setText("Unable to open dashboard.");
            e.printStackTrace();
        }

    } else {
        messageLabel.setText("Invalid username or password.");
        passwordField.clear();
    }
}

    @FXML
    private void handleExit() {

        Stage stage = (Stage) usernameField
            .getScene().getWindow();

        stage.close();
    }
}