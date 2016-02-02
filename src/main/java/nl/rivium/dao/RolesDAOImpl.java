package nl.rivium.dao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 1/19/2016.
 */
public class RolesDAOImpl implements RolesDAO {
    //@PersistenceContext(name = "issueUnit")
    //private EntityManager manager;
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     *
     * @param username for the user that is being logged in.
     * @return the userRole id, for which group the user has access to.
     */

    @Override
    public int userAccess (String username) {
        int userRole = 0;
        List<Integer> userRoleList;
        try {

            Query query = manager.createQuery
                    ("SELECT u.roles_id FROM User u where u.username = :username");

            query.setParameter("username", username);
            manager.getTransaction().commit();

            userRoleList = query.getResultList();
            userRole = userRoleList.get(0);

        } catch (IllegalStateException exception) {
            exception.getMessage();
        } finally {

        }

        return userRole;
    }
}
