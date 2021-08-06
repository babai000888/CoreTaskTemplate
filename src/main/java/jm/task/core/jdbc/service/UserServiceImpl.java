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
    }

    public void removeUserById(long id) {
        test.removeUserById(id);
    }

    public List<User> getAllUsers() {

        test.getAllUsers();
        return null;
    }

    public void cleanUsersTable() {
        test.cleanUsersTable();
    }
}
