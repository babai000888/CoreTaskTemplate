package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static String TABLE_NAME = "Users";
    private Connection connection;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public UserDaoJDBCImpl() {
    }

    void closeConnection() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT," +
                "  `name` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL," +
                "  `lastName` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL," +
                "  `age` SMALLINT NULL," +
                "  PRIMARY KEY (`id`))";
        try {
            Connection connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Created table " + TABLE_NAME);
        } catch (SQLException e) {
            System.out.println("Can not create table " + TABLE_NAME);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        try {
            Connection connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Dropped table " + TABLE_NAME);
        } catch (SQLException e) {
            System.out.println("Can not drop table " + TABLE_NAME);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }


    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (`id`, `name`, `lastName`, `age`) " +
                "   VALUES (DEFAULT, ?, ?, ?)";
        try {
            Connection connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + "/" + lastName + "/" + age + " created");
        } catch (SQLException e) {
            System.out.println("Can not save user " + name + "/" + lastName + "/" + age);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM "+ TABLE_NAME + " WHERE id = (" + id + ")";
        try {
            Connection connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int numDeleted = preparedStatement.executeUpdate();
            System.out.println("Amount of deleted users with id " + id + " : " + numDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM "+ TABLE_NAME;
        try {
            Connection connection = Util.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
            System.out.println("Received " + allUsers.size() + " users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return allUsers;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "TRUNCATE TABLE "+ TABLE_NAME;
        try {
            Connection connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Tables cleared");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
