package nl.rivium.dao;

import nl.rivium.entities.Status;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public class StatusDAOImpl implements StatusDAO {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Status> getStatusList() {
        List<Status> statusList = null;

        try{
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT s FROM Status s");

            statusList = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return statusList;
    }
}
