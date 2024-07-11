package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sm_resort",
                "root",
                "Ijse@123"
        );
    }

    public static DBConnection getInstance() throws SQLException {
        if(dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }



    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sm_resort",
                    "root",
                    "Ijse@123"
            );
        }
        return connection;
    }

}