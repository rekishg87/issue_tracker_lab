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
    private final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    //@PersistenceContext(name = "issueUnit")
    //private EntityManager manager;
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
        logger.info("User authentication API call started...");
        boolean foundUser = false;
        List<String> listUsers;

        try {
            manager.getTransaction().begin();
            logger.info("Query statement invoked.");
            Query query = manager.createQuery
                    ("SELECT u.password FROM User u where u.username = :username");

            query.setParameter("username", username);
            manager.getTransaction().commit();
            listUsers = query.getResultList();

            if(listUsers.isEmpty()) {
                logger.info("No User Found.");
                foundUser = false;
            } else {
                logger.info("User " + username + " found, logging in.");
                String uncheckedPass = listUsers.get(0);

                if(BCrypt.checkpw(password, uncheckedPass)) {
                    foundUser = true;
                } else {
                    foundUser = false;
                }
            }
        } catch (IllegalArgumentException  exception) {
            logger.error(exception.getMessage());
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
        logger.info("Register user API call started...");
        String addedUsername = "";

            try {
                List<String> listUsers;
                manager.getTransaction().begin();
                logger.info("Query method invoked.");
                Query query = manager.createQuery
                        ("SELECT u.username FROM User u where u.username = :username");

                query.setParameter("username", username);

                listUsers = query.getResultList();

                if(!listUsers.isEmpty()) {
                    logger.info("Duplicate user found.");
                    // If an existing user has been found, return the username.
                    // This means that a existing user has been found.
                    addedUsername = username;
                } else {
                    logger.info("No User Found, registering new user.");
                    User user = new User();
                    user.setUsername(username);
                    // Encrypt the password first, before storing in the database.
                    user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                    user.setEmail(email);
                    user.setRoles_id(2); // Default value for the User group when a new user registers.
                    manager.persist(user);
                    manager.getTransaction().commit();
                    logger.info("USer persisted " + user);
                }

            } catch (IllegalArgumentException | EntityExistsException |
                    TransactionRequiredException  exception) {
                logger.error(exception.getMessage());
            }
        return addedUsername;
    }
}
