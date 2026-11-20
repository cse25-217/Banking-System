package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {

        String customersTable = """
            CREATE TABLE IF NOT EXISTS customers (
                customer_id TEXT PRIMARY KEY,
                first_name TEXT NOT NULL,
                surname TEXT NOT NULL,
                address TEXT NOT NULL,
                phone_number TEXT NOT NULL,
                email TEXT NOT NULL,
                employed INTEGER NOT NULL
            )
            """;

        String accountsTable = """
            CREATE TABLE IF NOT EXISTS accounts (
                account_number TEXT PRIMARY KEY,
                customer_id TEXT NOT NULL,
                account_type TEXT NOT NULL,
                balance REAL NOT NULL DEFAULT 0,
                branch TEXT NOT NULL,
                opening_date TEXT NOT NULL,
                employer_name TEXT,
                employer_address TEXT,
                FOREIGN KEY (customer_id)
                    REFERENCES customers(customer_id)
            )
            """;

        String transactionsTable = """
            CREATE TABLE IF NOT EXISTS transactions (
                transaction_id TEXT PRIMARY KEY,
                account_number TEXT NOT NULL,
                amount REAL NOT NULL,
                transaction_type TEXT NOT NULL,
                description TEXT,
                transaction_date TEXT NOT NULL,
                FOREIGN KEY (account_number)
                    REFERENCES accounts(account_number)
            )
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(customersTable);
            statement.execute(accountsTable);
            statement.execute(transactionsTable);

            System.out.println("All database tables created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
