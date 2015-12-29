package nl.rivium.dao;

import nl.rivium.entities.Priority;
import org.hibernate.HibernateException;

import javax.ejb.EJBException;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Retrieve data from the Priority table in the database
 */
public class PriorityDAOImpl implements PriorityDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     *
     * @return all the possible options from the Priority table as a List<>
     */
    @Override
    public List<Priority> getPriorityList() {
        List<Priority> priorityList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT p FROM Priority p");
            manager.getTransaction().commit();
            priorityList = query.getResultList();


        } catch (EJBException ex) {
            ex.printStackTrace();
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return priorityList;
    }

}
