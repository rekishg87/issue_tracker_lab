package nl.rivium.dao;

import nl.rivium.connection.JPAConnection;
import nl.rivium.entities.Priority;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public class PriorityDAOImpl implements PriorityDAO{
    //private static EntityManager manager = JPAConnection.createEntityManager();
    EntityManager manager;

    @Override
    public List<Priority> getPriorityList() {
        List<Priority> priorityList = null;

        try{
            manager = JPAConnection.createEntityManager();
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT p FROM Priority p");

            priorityList = query.getResultList();
            manager.getTransaction().commit();

        } catch (HibernateException ex) {
            try {
                if (manager.getTransaction().isActive()){
                    manager.getTransaction().rollback();
                }
            } catch (Throwable rollBackException) {
                System.out.println("Could not rollback after exception! " + rollBackException);
                rollBackException.printStackTrace();
            }
        }
        finally {
            manager.close();
        }
        return priorityList;
    }

}
