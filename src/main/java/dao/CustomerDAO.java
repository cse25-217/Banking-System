package dao;
import database.DatabaseConnection;
import model.Customer;
import util.PasswordUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public Customer findById(String customerId) throws SQLException {

        String sql = """
            SELECT customer_id, first_name, surname,
                   address, phone_number, email, employed
            FROM customers
            WHERE customer_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customerId);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return new Customer(
                        result.getString("customer_id"),
                        result.getString("first_name"),
                        result.getString("surname"),
                        result.getString("address"),
                        result.getString("phone_number"),
                        result.getString("email"),
                        result.getInt("employed") == 1
                    );
                }
            }
        }

        return null;
    }

    public Customer authenticate(String username, String password)
            throws Exception {

        String sql = """
            SELECT customer_id, password_hash
            FROM customers
            WHERE username = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    String customerId = result.getString("customer_id");
                    String storedHash = result.getString("password_hash");

                    if (storedHash != null &&
                        PasswordUtil.verifyPassword(password, storedHash)) {

                        return findById(customerId);
                    }
                }
            }
        }

        return null;
    }
}
