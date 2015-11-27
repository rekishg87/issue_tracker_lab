package nl.rivium.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Rekish on 11/26/2015.
 */
@WebListener
public class JPAConnection implements ServletContextListener {

    private static EntityManagerFactory entityManagerFactory;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        entityManagerFactory = Persistence.createEntityManagerFactory("issueUnit");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        entityManagerFactory.close();
    }

    public static EntityManager createEntityManager() {
        if (entityManagerFactory == null) {
            throw new java.lang.IllegalStateException("Context is not initialized yet.");
        }
        return entityManagerFactory.createEntityManager();
    }
}
