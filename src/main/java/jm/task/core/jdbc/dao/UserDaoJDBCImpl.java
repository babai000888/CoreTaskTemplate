package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT," +
                "  `name` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL," +
                "  `lastName` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL," +
                "  `age` SMALLINT NULL," +
                "  PRIMARY KEY (`id`))";
        try ( Statement statement = Util.getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(" createUsersTable ERROR\n" + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        try ( Statement statement = Util.getConnection().createStatement()){
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("dropUsersTable ERROR\n" + e.getMessage());
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users" +
                " (`id`, `name`, `lastName`, `age`) " +
                "   VALUES (DEFAULT, ?, ?, ?)";
        try ( PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql) ){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("saveUser ERROR\n" + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE `id` = ? ";
        try ( PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql) ){
            preparedStatement.setString(1, Long.toString(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeUserById ERROR\n" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try ( ResultSet resultSet = Util.getConnection().createStatement().executeQuery(sql) ){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("getAllUsers ERROR\n" + e.getMessage());
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE Users";
        try ( Statement statement = Util.getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("cleanUsersTable ERROR\n" + e.getMessage());
        }
    }
}
