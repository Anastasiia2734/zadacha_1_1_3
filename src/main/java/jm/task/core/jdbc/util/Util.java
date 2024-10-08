package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://private.oxfraud.cc:3306/user_04102024";
    private static final String DB_LOGIN = "user_04102024";
    private static final String DB_PASSWORD = "04102024oO!";
    private static Connection connection = null;

    private Util() {

    }


    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}