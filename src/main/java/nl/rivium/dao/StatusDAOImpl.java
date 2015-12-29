package nl.rivium.dao;

import nl.rivium.entities.Status;
import org.hibernate.HibernateException;

import javax.ejb.EJBException;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Retrieve data from the Priority table in the database
 */
public class StatusDAOImpl implements StatusDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     *
     * @return all the possible options from the Status table as a List<>
     */
    @Override
    public List<Status> getStatusList() {
        List<Status> statusList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT s FROM Status s");
            manager.getTransaction().commit();
            statusList = query.getResultList();


        } catch (EJBException ex) {
            ex.printStackTrace();
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return statusList;
    }
}
