package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;

public class Main{
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Дао","Юззер",(byte)23);
        userService.saveUser("Мумий","Троль",(byte)23);
        userService.saveUser("Ким","Чен",(byte)23);
        userService.saveUser("Тип","Топ",(byte)23);
        userService.getAllUsers();
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        System.out.println("Main is finished");
    }
}

