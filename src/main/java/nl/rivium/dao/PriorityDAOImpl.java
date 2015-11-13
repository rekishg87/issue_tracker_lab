package nl.rivium.dao;

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
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Priority> getPriorityList() {
        List<Priority> priorityList = null;

        try{
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT p FROM Priority p");

            priorityList = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return priorityList;
    }

}
