package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {

    public static void goToDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                Navigation.class.getResource("/view/Dashboard.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("NOVA BANK - Dashboard");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}