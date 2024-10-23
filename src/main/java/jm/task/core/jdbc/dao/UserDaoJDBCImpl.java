package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    private UserDao userDao;

    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
    }

    @Override
    public void createUsersTable() {
        executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), lastname VARCHAR(20), age TINYINT NOT NULL)");
    }

    @Override
    public void dropUsersTable() {
        executeUpdate("DROP TABLE IF EXISTS users");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
       String query = "INSERT INTO users (name, lastName, age) values (?, ?, ?)";
       try {
           connection.setAutoCommit(false);
           try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
               preparedStatement.setString(1, name);
               preparedStatement.setString(2, lastName);
               preparedStatement.setByte(3, age);
               preparedStatement.executeUpdate();
           }
           connection.commit();
       }
       catch (SQLException e) {
            rollback();
            e.printStackTrace();
        } finally {
           try {
               connection.setAutoCommit(true);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }

    @Override
    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } connection.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User userObj = new User();
                userObj.setId(resultSet.getLong("id"));
                userObj.setName((resultSet.getString("name")));
                userObj.setLastName(resultSet.getString("lastName"));
                userObj.setAge((byte) resultSet.getInt("age"));
                users.add(userObj);
            }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);

            executeUpdate("DELETE FROM users");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeUpdate(String request) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
