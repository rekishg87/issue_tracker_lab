package nl.rivium.dao;

import nl.rivium.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 9/17/2015.
 * Authorize and create a new user.
 */

public class UserDAOImpl implements UserDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     *
     * @param username to be checked in the database
     * @param password to be decrypted by JBCrypt and checked for the right match in the database
     * @return if user has entered correct information corresponding with the database
     */
    @Override
    public boolean authenticate (String username, String password) {
        boolean foundUser = false;
        List<String> listUsers;

        try {
            manager.getTransaction().begin();

            Query query = manager.createQuery
                    ("SELECT u.password FROM User u where u.username = :username");

            query.setParameter("username", username);
            manager.getTransaction().commit();
            listUsers = query.getResultList();

            if(listUsers.isEmpty()) {
                foundUser = false;
            } else {
                String uncheckedPass = listUsers.get(0);

                if(BCrypt.checkpw(password, uncheckedPass)) {
                    foundUser = true;
                } else {
                    foundUser = false;
                }
            }
        } catch (EJBException ex) {
            ex.printStackTrace();
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return foundUser;
    }

    /**
     *
     * @param username to be persisted in the database for the new user
     * @param password to be persisted, encrypted by JBCrypt in the database for the new user
     * @param email to be persisted in the database for the new user
     * @return the username as a String that has been persisted in the database for the new registered user
     */
    @Override
    public String registerUser (String username, String password, String email) {

        String addedUsername = "";

        // If all fields or one of the fields of username or password is empty,
        // no user will be registered because of incomplete information
        if(username == null || username.equals("") || password == null || password.equals("")) {
            addedUsername = null;
        } else {

            try {
                List<String> listUsers;
                manager.getTransaction().begin();

                Query query = manager.createQuery
                        ("SELECT u.username FROM User u where u.username = :username");

                query.setParameter("username", username);
                manager.getTransaction().commit();
                listUsers = query.getResultList();

                if(!listUsers.isEmpty()) {
                    // If an existing user has been found, return an empty String
                    // which means that no new user has been created
                    addedUsername = "";
                } else {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                    user.setEmail(email);
                    user.setRoles_id(2); // Default value for the User group when a new user registers.
                    manager.persist(user);
                    addedUsername = username;
                }

            } catch (EJBException ex) {
                ex.printStackTrace();
            } finally {
                if (manager.getTransaction().isActive()){
                    manager.getTransaction().rollback();
                    manager.close();
                    factory.close();
                }
            }
        }

        return addedUsername;
    }
}
