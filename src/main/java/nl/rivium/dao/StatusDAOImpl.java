package nl.rivium.dao;

import nl.rivium.entities.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Retrieve data from the Priority table in the database
 */
public class StatusDAOImpl implements StatusDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusDAOImpl.class);
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private static final String EXCEPTION_STRING = "Exception Occurred";

    /**
     *
     * @return all the possible options from the Status table as a List<>
     */
    @Override
    public List<Status> getStatusList() {
        LOGGER.info("Getting all status options.");
        List<Status> statusList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT s FROM Status s");
            manager.getTransaction().commit();
            statusList = query.getResultList();
        } catch (IllegalStateException | RollbackException exception) {
            LOGGER.error(EXCEPTION_STRING, exception);
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
