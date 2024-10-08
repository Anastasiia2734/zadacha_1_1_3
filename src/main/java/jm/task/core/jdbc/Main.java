package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 22);
        userService.saveUser("Петри", "Контиола", (byte) 40);
        userService.saveUser("Зак", "Фукале", (byte) 27);
        userService.saveUser("Максим", "Шабанов", (byte) 24);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.getConnection();
    }
}
