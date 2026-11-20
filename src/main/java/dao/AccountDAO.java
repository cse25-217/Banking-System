package dao;
import database.DatabaseConnection;
import model.Account;
import model.Customer;
import model.ChequeAccount;
import model.SavingsAccount;
import model.InvestmentAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public void saveAccount(Account account) throws SQLException {

        String sql = """
            INSERT INTO accounts (
                account_number, customer_id, account_type,
                balance, branch, opening_date,
                employer_name, employer_address
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getAccountNumber());
            statement.setString(2, account.getCustomer().getCustomerId());
            statement.setString(3, account.getAccountType());
            statement.setDouble(4, account.getBalance());
            statement.setString(5, account.getBranch());
            statement.setString(6, account.getOpeningDate().toString());

            if (account instanceof ChequeAccount cheque) {
                statement.setString(7, cheque.getEmployerName());
                statement.setString(8, cheque.getEmployerAddress());
            } else {
                statement.setNull(7, Types.VARCHAR);
                statement.setNull(8, Types.VARCHAR);
            }

            statement.executeUpdate();
            System.out.println("Account saved successfully!");
        }
    }

    public List<Account> findByCustomerId(String customerId)
            throws SQLException {

        List<Account> accounts = new ArrayList<>();

        Customer customer = new CustomerDAO().findById(customerId);

        if (customer == null) {
            return accounts;
        }

        String sql = """
            SELECT account_number, account_type, balance,
                   branch, employer_name, employer_address
            FROM accounts
            WHERE customer_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customerId);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    String number = result.getString("account_number");
                    String type = result.getString("account_type");
                    double balance = result.getDouble("balance");
                    String branch = result.getString("branch");

                    Account account = null;

                    switch (type) {
                        case "Savings Account" ->
                            account = new SavingsAccount(
                                number, balance, branch, customer
                            );

                        case "Investment Account" ->
                            account = new InvestmentAccount(
                                number, balance, branch, customer
                            );

                        case "Cheque Account" ->
                            account = new ChequeAccount(
                                number, balance, branch, customer,
                                result.getString("employer_name"),
                                result.getString("employer_address")
                            );
                    }

                    if (account != null) {
                        accounts.add(account);
                    }
                }
            }
        }

        return accounts;
    }
}
