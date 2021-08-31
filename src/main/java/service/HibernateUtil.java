package service;


import model.Password;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            try {
                Configuration config = new Configuration();
                Properties settings = new Properties();
//                com.microsoft.sqlserver.jdbc.SQLServerDriver
//                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
//                settings.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=ps;integratedSecurity=true");
                settings.put(Environment.URL, "jdbc:postgresql://192.168.0.60:5432/ps");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "Engine1600");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create");

                config.setProperties(settings);
                config.addAnnotatedClass(Password.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties())
                        .build();

                sessionFactory = config.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Wrapper exception: " + e);
                System.out.println("Underlying exception: " + e.getCause());
            }
        }
        return sessionFactory;
    }
}
