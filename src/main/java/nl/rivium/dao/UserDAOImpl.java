package nl.rivium.dao;

import nl.rivium.entities.User;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 9/17/2015.
 * Authorize and create a new user.
 */
@Stateless
public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private static final String EXCEPTION_STRING = "Exception Occurred";
    public static final int GROUP_USER = 2;

    /**
     *
     * @param username to be checked in the database
     * @param password to be decrypted by JBCrypt and checked for the right match in the database
     * @return if user has entered correct information corresponding with the database
     */
    @Override
    public boolean authenticate (String username, String password) {
        LOGGER.info("User authentication API call started...");
        boolean foundUser = false;
        List<String> listUsers;

        try {
            manager.getTransaction().begin();
            LOGGER.info("Query statement invoked.");
            Query query = manager.createQuery
                    ("SELECT u.password FROM User u where u.username = :username");

            query.setParameter("username", username);
            manager.getTransaction().commit();
            listUsers = query.getResultList();

            if(listUsers.isEmpty()) {
                LOGGER.info("No User Found.");
                foundUser = false;
            } else {
                LOGGER.info("User " + username + " found, logging in.");
                String uncheckedPass = listUsers.get(0);

                if(BCrypt.checkpw(password, uncheckedPass)) {
                    foundUser = true;
                } else {
                    foundUser = false;
                }
            }
        } catch (IllegalStateException | RollbackException | IllegalArgumentException exception) {
            LOGGER.error(EXCEPTION_STRING, exception);
        }
        return foundUser;
    }

    /**
     *
     * @param username to be persisted in the database for the new user
     * @param password to be persisted, encrypted by JBCrypt in the database for the new user
     * @param email to be persisted in the database for the new user
     * @return the username that has been entered, to check if the username is available.
     */

    @Override
    public String registerUser (String username, String password, String email) {
        LOGGER.info("Register user API call started...");
        String addedUsername = "";


            try {
                List<String> listUsers;
                manager.getTransaction().begin();
                LOGGER.info("Query method invoked.");
                Query query = manager.createQuery
                        ("SELECT u.username FROM User u where u.username = :username");

                query.setParameter("username", username);

                listUsers = query.getResultList();

                if(!listUsers.isEmpty()) {
                    LOGGER.info("Duplicate user found.");
                    // If an existing user has been found, return the username.
                    // This means that a existing user has been found.
                    addedUsername = username;
                } else {

                    LOGGER.info("No User Found, registering new user.");
                    User user = new User();
                    user.setUsername(username);
                    // Encrypt the password first, before storing in the database.
                    user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                    user.setEmail(email);
                    // Default value for the User group when a new user registers.
                    user.setRolesId(GROUP_USER);
                    manager.persist(user);
                    manager.getTransaction().commit();
                    LOGGER.info("USer persisted " + user);
                }

            } catch (IllegalArgumentException | EntityExistsException |
                    TransactionRequiredException  exception) {
                    LOGGER.error(EXCEPTION_STRING, exception);
            }
        return addedUsername;
    }
}
