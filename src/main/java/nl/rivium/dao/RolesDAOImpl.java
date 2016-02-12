package nl.rivium.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 1/19/2016.
 */
public class RolesDAOImpl implements RolesDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(RolesDAOImpl.class);
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private static final String EXCEPTION_STRING = "Exception Occurred";

    /**
     *
     * @param username for the user that is being logged in.
     * @return the userRole id, for which group the user has access to.
     */

    @Override
    public int userAccess (String username) {
        LOGGER.info("Getting user role.");
        int userRole = 0;
        List<Integer> userRoleList;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT u.rolesId FROM User u where u.username = :username");

            query.setParameter("username", username);
            manager.getTransaction().commit();

            userRoleList = query.getResultList();
            userRole = userRoleList.get(0);
        } catch (IllegalStateException | RollbackException exception) {
            LOGGER.error(EXCEPTION_STRING, exception);
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return userRole;
    }
}
