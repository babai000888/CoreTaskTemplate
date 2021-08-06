package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.dao.*;

import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
UserDao test = new UserDaoJDBCImpl();

test.createUsersTable();
test.saveUser("Дао","Юззер",(byte)23);
test.saveUser("Мумий","Троль",(byte)23);
test.saveUser("Ким","Чен",(byte)23);
test.saveUser("Тип","Топ",(byte)23);
test.removeUserById(7);
for (User user : test.getAllUsers()) {
    System.out.println(user.toString());
}
test.cleanUsersTable();
test.dropUsersTable();








    }
}
