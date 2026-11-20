package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DatabaseConnection;
import model.Transaction;

public class TransactionDAO {

    public void saveTransaction(
            Transaction transaction,
            String accountNumber) throws SQLException {

        String sql = """
            INSERT INTO transactions (
                transaction_id, account_number, amount,
                transaction_type, description, transaction_date
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transaction.getTransactionId());
            statement.setString(2, accountNumber);
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getTransactionType());
            statement.setString(5, transaction.getDescription());
            statement.setString(
                6, transaction.getTransactionDate().toString()
            );

            statement.executeUpdate();

            System.out.println("Transaction saved successfully!");
        }
    }
}
