package nl.rivium.dao;

import nl.rivium.entities.Priority;
import org.hibernate.HibernateException;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public class PriorityDAOImpl implements PriorityDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Priority> getPriorityList() throws IllegalStateException {
        List<Priority> priorityList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT p FROM Priority p");

            priorityList = query.getResultList();


        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return priorityList;
    }

}
