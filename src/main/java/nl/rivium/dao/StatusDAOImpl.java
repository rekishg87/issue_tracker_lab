package nl.rivium.dao;

import nl.rivium.entities.Status;
import org.hibernate.HibernateException;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public class StatusDAOImpl implements StatusDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Status> getStatusList() throws IllegalStateException {
        List<Status> statusList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT s FROM Status s");

            statusList = query.getResultList();


        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return statusList;
    }
}
