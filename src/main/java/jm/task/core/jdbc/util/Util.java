package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://private.oxfraud.cc:3306/user_04102024";
    private static final String DB_LOGIN = "user_04102024";
    private static final String DB_PASSWORD = "04102024oO!";

    private Util() {

    }


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}