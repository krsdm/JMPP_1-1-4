package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String DB_USER = "root";
    private static final String DB_PASS = "K5639667Dimon";
    private static final String DB_NAME = "usersdb";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Properties properties = new Properties();
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.setProperty(Environment.SHOW_SQL, "true");
            properties.setProperty(Environment.USER, DB_USER);
            properties.setProperty(Environment.PASS, DB_PASS);
            properties.setProperty(Environment.URL, DB_URL);

            sessionFactory = new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

        } catch (HibernateException e) {
            System.out.println("Ошибка при создании сессии");
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
