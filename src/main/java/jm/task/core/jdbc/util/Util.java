package jm.task.core.jdbc.util;


import jm.task.core.jdbc.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String username="Max";
    private static final String password="1111";
    private static final String conectionURL="jdbc:mysql://localhost:3306/schema_1_1_3";
    private static final String Driver = "com.mysql.cj.jdbc.Driver";

    private static SessionFactory sessionFactory;

    private Util() {}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();

                settings.setProperty("hibernate.connection.driver_class", Driver);
                settings.setProperty("hibernate.connection.username", username);
                settings.setProperty("hibernate.connection.password", password);
                settings.setProperty("hibernate.connection.url", conectionURL);
                settings.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                settings.setProperty("hibernate.hbm2ddl.auto","update");
                settings.setProperty("hibernate.show_sql", "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


    public static Connection getConnection () {

        Connection connection=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(conectionURL, username, password);
            if (!connection.isClosed()) {
                System.out.println("Connection is ready");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return connection;
    }
}
