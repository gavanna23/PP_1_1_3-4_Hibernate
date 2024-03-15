package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .addAnnotatedClass(User.class)
                        .setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                        .setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mybd")
                        .setProperty(Environment.USER, "root")
                        .setProperty(Environment.PASS, "root")
                        .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect")
                        .setProperty(Environment.SHOW_SQL, "true")
                        .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                        .setProperty(Environment.HBM2DDL_AUTO, "")
                        .setProperty(Environment.AUTOCOMMIT, "true");

                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                registryBuilder.applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = registryBuilder.build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}


