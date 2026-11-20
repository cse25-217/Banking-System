
package controller;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyAccountsController {

    @FXML private TableView<String[]> accountsTable;

    @FXML private TableColumn<String[], String> accountNumberColumn;
    @FXML private TableColumn<String[], String> accountTypeColumn;
    @FXML private TableColumn<String[], String> balanceColumn;
    @FXML private TableColumn<String[], String> branchColumn;
    @FXML private TableColumn<String[], String> openingDateColumn;

    private ObservableList<String[]> accountData =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        accountNumberColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[0]));

        accountTypeColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[1]));

        balanceColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[2]));

        branchColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[3]));

        openingDateColumn.setCellValueFactory(
            data -> new ReadOnlyStringWrapper(data.getValue()[4]));

        accountsTable.setItems(accountData);

        loadAccounts();
    }

    private void loadAccounts() {
        // Customer accounts will be retrieved from the database here.
        accountsTable.setItems(accountData);
    }
    
    @FXML
    private void handleRefreshAccounts() {
        loadAccounts();
    }

    @FXML
    private void handleOpenAccount() {
        System.out.println("Open New Account selected.");
        // Navigation to the Open Account screen will be connected next.
    }
}
