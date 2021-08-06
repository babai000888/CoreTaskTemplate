package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static String TABLE_NAME = "`CoreTaskTemplate`.`Users`";
    private Connection connection = Util.getConnection();
    Statement statement = null;
    PreparedStatement preparedStatement = null;

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
                "  `name` CHAR CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL," +
                "  `lastName` CHAR CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL," +
                "  `age` SMALLINT NULL," +
                "  PRIMARY KEY (`id`))";
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Created table 'Users'");
        } catch (SQLException e) {
            System.out.println("Can not create table 'Users'");
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Dropped table 'Users'");
        } catch (SQLException e) {
            System.out.println("Can not drop table 'Users'");
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
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
