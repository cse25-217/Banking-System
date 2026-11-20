
package controller;
import java.time.LocalDate;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransactionsController {

    @FXML private ComboBox<String> transactionTypeComboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private TableView<String[]> transactionsTable;

    @FXML private TableColumn<String[], String> transactionIdColumn;
    @FXML private TableColumn<String[], String> accountNumberColumn;
    @FXML private TableColumn<String[], String> transactionTypeColumn;
    @FXML private TableColumn<String[], String> amountColumn;
    @FXML private TableColumn<String[], String> descriptionColumn;
    @FXML private TableColumn<String[], String> dateColumn;

    @FXML private Label messageLabel;

    private ObservableList<String[]> transactionData =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        transactionTypeComboBox.getItems().addAll(
            "All",
            "Deposit",
            "Withdrawal",
            "Interest"
        );

        transactionTypeComboBox.setValue("All");

        transactionIdColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[0]));

        accountNumberColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[1]));

        transactionTypeColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[2]));

        amountColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[3]));

        descriptionColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[4]));

        dateColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[5]));

        transactionsTable.setItems(transactionData);

        loadTransactions();
    }

    private void loadTransactions() {
        // Transactions will be retrieved from the database here.
        transactionsTable.setItems(transactionData);
    }
    @FXML
private void handleBackToDashboard(ActionEvent event) {
    Navigation.goToDashboard(event);
}
    @FXML
    private void handleSearch() {

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate != null && endDate != null
                && startDate.isAfter(endDate)) {

            messageLabel.setText(
                "Start date cannot be after end date."
            );
            return;
        }

        String selectedType = transactionTypeComboBox.getValue();

        messageLabel.setText(
            "Filters validated. Transaction search will use the database."
        );

        // Database filtering will be implemented during JDBC integration.
    }
}
