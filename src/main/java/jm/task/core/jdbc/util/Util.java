package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CoreTaskTemplate?useSSL=false&useUnicode=true&characterEncoding=utf8",
                        "root", "000888");
            } catch (SQLException e) {
                System.out.println(" getConnection() ERROR");
            }
        }
        return connection;
    }
}


