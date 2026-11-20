package controller;

import java.util.List;

import dao.AccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Account;
import model.Customer;
import util.Session;

public class DashboardController {

    @FXML private Label totalBalanceLabel;
    @FXML private Label activeAccountsLabel;

    @FXML private Label savingsBalanceLabel;
    @FXML private Label savingsNumberLabel;

    @FXML private Label investmentBalanceLabel;
    @FXML private Label investmentNumberLabel;

    @FXML private Label chequeBalanceLabel;
    @FXML private Label chequeNumberLabel;

    @FXML private TableView<?> recentTransactionsTable;

    @FXML private TableColumn<?, ?> recentTransactionIdColumn;
    @FXML private TableColumn<?, ?> recentTransactionTypeColumn;
    @FXML private TableColumn<?, ?> recentAmountColumn;
    @FXML private TableColumn<?, ?> recentDateColumn;

    @FXML
    public void initialize() {

        totalBalanceLabel.setText("BWP 0.00");
        activeAccountsLabel.setText("0");

        savingsBalanceLabel.setText("BWP 0.00");
        savingsNumberLabel.setText("No account yet");

        investmentBalanceLabel.setText("BWP 0.00");
        investmentNumberLabel.setText("No account yet");

        chequeBalanceLabel.setText("BWP 0.00");
        chequeNumberLabel.setText("No account yet");

        loadCustomerAccounts();
    }

    private void loadCustomerAccounts() {

        Customer customer = Session.getCurrentCustomer();

        if (customer == null) {
            return;
        }

        try {

            AccountDAO accountDAO = new AccountDAO();

            List<Account> accounts =
                accountDAO.findByCustomerId(customer.getCustomerId());

            double totalBalance = 0;

            double savingsBalance = 0;
            double investmentBalance = 0;
            double chequeBalance = 0;

            int savingsCount = 0;
            int investmentCount = 0;
            int chequeCount = 0;

            for (Account account : accounts) {

                totalBalance += account.getBalance();

                switch (account.getAccountType()) {

                    case "Savings Account":
                        savingsBalance += account.getBalance();
                        savingsCount++;
                        break;

                    case "Investment Account":
                        investmentBalance += account.getBalance();
                        investmentCount++;
                        break;

                    case "Cheque Account":
                        chequeBalance += account.getBalance();
                        chequeCount++;
                        break;
                }
            }

            totalBalanceLabel.setText(
                String.format("BWP %.2f", totalBalance)
            );

            activeAccountsLabel.setText(
                String.valueOf(accounts.size())
            );

            savingsBalanceLabel.setText(
                String.format("BWP %.2f", savingsBalance)
            );

            savingsNumberLabel.setText(
                savingsCount == 0 ? "No account yet" :
                savingsCount + " account(s)"
            );

            investmentBalanceLabel.setText(
                String.format("BWP %.2f", investmentBalance)
            );

            investmentNumberLabel.setText(
                investmentCount == 0 ? "No account yet" :
                investmentCount + " account(s)"
            );

            chequeBalanceLabel.setText(
                String.format("BWP %.2f", chequeBalance)
            );

            chequeNumberLabel.setText(
                chequeCount == 0 ? "No account yet" :
                chequeCount + " account(s)"
            );

        } catch (Exception e) {
            System.err.println("Unable to load customer accounts.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDashboard() {
    }

    @FXML
    private void handleMyAccounts() {
        openScreen("/view/my_accounts.fxml", "My Accounts");
    }

    @FXML
    private void handleOpenAccount() {
        openScreen("/view/open_account.fxml", "Open Account");
    }

    @FXML
    private void handleDeposit() {
        openScreen("/view/Deposit.fxml", "Deposit Funds");
    }

    @FXML
    private void handleWithdraw() {
        openScreen("/view/Withdraw.fxml", "Withdraw Funds");
    }

    @FXML
    private void handleTransactions() {
        openScreen("/view/transaction_history.fxml", "Transaction History");
    }

    @FXML
    private void handleProfile() {
        openScreen("/view/my_profile.fxml", "My Profile");
    }

    @FXML
    private void handleLogout() {
        Session.clearSession();
        openScreen("/view/Login.fxml", "Login");
    }

    private void openScreen(String filePath, String title) {

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource(filePath)
            );

            Parent root = loader.load();

            Stage stage = (Stage) totalBalanceLabel
                .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("NOVA BANK - " + title);
            stage.show();

        } catch (Exception e) {
            System.out.println("Unable to open screen: " + filePath);
            e.printStackTrace();
        }
    }
}