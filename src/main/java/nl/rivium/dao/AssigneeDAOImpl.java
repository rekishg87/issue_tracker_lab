package nl.rivium.dao;

import nl.rivium.entities.Assignee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Retrieve data from the Assignee table in the database
 */
public class AssigneeDAOImpl implements AssigneeDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssigneeDAOImpl.class);
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private static final String EXCEPTION_STRING = "Exception Occurred";

    /**
     *
     * @return all the possible options from the assignee table as a List<>
     */
    @Override
    public List<Assignee> getAssigneeList() {
        LOGGER.info("Getting all assignee's.");
        List<Assignee> assigneeList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT a FROM Assignee a");
            manager.getTransaction().commit();
            assigneeList = query.getResultList();
        } catch (IllegalStateException exception) {
            LOGGER.error(EXCEPTION_STRING, exception);
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }
        return assigneeList;
    }
}
