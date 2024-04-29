package infrastructure;

import java.sql.Connection;
import repositories.Loggable;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig implements Loggable {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM553448";
    private static final String PASSWORD = "110979";
    public Connection getConnection() {
        try {
            logInfo("Successful database connection");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logError("Could not connect to the database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void closeConnection() {
        try {
            logInfo("Database connection closed");
            this.getConnection().close();
        } catch (SQLException e) {
            logError("Error closing database connection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
