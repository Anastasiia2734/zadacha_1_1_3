package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        UserServiceImpl userService = new UserServiceImpl();
        try {
            Util.getConnection();
            userService.createUsersTable();
            userService.saveUser("Иван", "Иванов", (byte) 22);
            userService.saveUser("Петри", "Контиола", (byte) 40);
            userService.saveUser("Зак", "Фукале", (byte) 27);
            userService.saveUser("Максим", "Шабанов", (byte) 24);
            userService.getAllUsers();
            userService.cleanUsersTable();
            userService.dropUsersTable();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (userService != null) {
                try {
                    userService.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
