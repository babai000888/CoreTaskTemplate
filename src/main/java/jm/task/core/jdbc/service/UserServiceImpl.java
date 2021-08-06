package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao test = new UserDaoJDBCImpl();

    public void createUsersTable() {
        test.createUsersTable();
    }

    public void dropUsersTable() {
        test.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        test.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных" );
    }

    public void removeUserById(long id) {
        test.removeUserById(id);
    }

    public List<User> getAllUsers() {
        for (User user : test.getAllUsers()) {
            System.out.println(user.toString());
        }
        return test.getAllUsers();
    }

    public void cleanUsersTable() {
        test.cleanUsersTable();
    }
}
