package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String USR = "root";
    static final String PWD = "000888";
    static final String URL = "jdbc:mysql://localhost:3306/CoreTaskTemplate?useSSL=false&useUnicode=true&characterEncoding=utf8";
    static Connection connection = null;
    public static Connection getConnection() {
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connection OK");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection ERROR");
            throw new RuntimeException(e);
        }
    }
}
