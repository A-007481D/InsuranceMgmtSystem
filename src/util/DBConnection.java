package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    private static DBConnection instance;
    private final Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/assurance";
    private static final String USER = "malik";
    private static final String PASSWORD = "malik";

    private DBConnection(){
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            this.connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("failed to connect", e);
        }
    }


    private static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }





}
