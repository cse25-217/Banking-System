package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:nova_bank.db";

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(URL);

        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        }

        return connection;
    }

    public static void main(String[] args) {

        try (Connection connection = getConnection()) {
            System.out.println("NOVA BANK DATABASE CONNECTED SUCCESSFULLY!");
        } catch (SQLException e) {
            System.out.println("DATABASE CONNECTION FAILED!");
            e.printStackTrace();
        }
    }
}
