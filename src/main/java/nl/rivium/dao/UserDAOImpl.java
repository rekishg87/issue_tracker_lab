package nl.rivium.dao;

import nl.rivium.connection.DBConnection;
import nl.rivium.entities.User;
import org.hibernate.HibernateException;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rekish on 9/17/2015.
 */

public class UserDAOImpl implements UserDAO {
    //EntityManager manager = DBConnection.createEntityManager();
    EntityManager manager;

    @Context
    private static HttpServletRequest request;

    @Override
    public boolean auth(String username, String password) {
        boolean found = false;
        List<String> listUsers;

        try{
            manager = DBConnection.createEntityManager();
            manager.getTransaction().begin();

            Query query = manager.createQuery
                    ("SELECT u.password FROM User u where u.username = :username");

            query.setParameter("username", username);

            listUsers = query.getResultList();

            if(listUsers.isEmpty()) {
                found = false;
            } else {
                String uncheckedPass = listUsers.get(0);

                if(BCrypt.checkpw(password, uncheckedPass)) {
                    found = true;
                } else {
                    found = false;
                }
            }
        } catch (HibernateException ex) {
            try {
                if (manager.getTransaction().isActive()){
                    manager.getTransaction().rollback();
                }
            } catch (Throwable rollBackException) {
                System.out.println("Could not rollback after exception! " + rollBackException);
                rollBackException.printStackTrace();
            }
        }
        finally {
            manager.close();
        }

        return found;
    }

    @Override
    public List<User> signupUser (String username, String password, String email) {

        List<User> userAdded = new ArrayList<>();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        if(username == null || username.equals("") || password == null || password.equals("")) {
            userAdded = null;
        } else {

            try {
                manager = DBConnection.createEntityManager();
                List<String> listUsers;
                manager.getTransaction().begin();

                Query query = manager.createQuery
                        ("SELECT u.username FROM User u where u.username = :username");

                query.setParameter("username", username);

                listUsers = query.getResultList();
                if(listUsers.isEmpty()) {
                    System.out.println("No Users Found!");
                } else {
                    String checkUsername = listUsers.get(0);
                    System.out.println("UsernameCheck: " + checkUsername);
                }


                if(!listUsers.isEmpty()) {
                    System.out.println("If statement");
                    //userAdded.isEmpty();

                } else {

                    System.out.println("Else statement");
                    //transaction.begin();
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                    user.setEmail(email);
                    user.setRoles_id(2);
                    manager.persist(user);
                    userAdded.add(user);
                    //transaction.commit();
                    manager.getTransaction().commit();
                    System.out.println("userAdded: " +  userAdded);
                }

            } catch (IndexOutOfBoundsException ex) {
                try {
                    if (manager.getTransaction().isActive()){
                        manager.getTransaction().rollback();
                    }
                } catch (Throwable rollBackException) {
                    System.out.println("Could not rollback after exception! " + rollBackException);
                    rollBackException.printStackTrace();
                }
            }
            finally {
                manager.close();
            }
        }
        return userAdded;
    }

    /*public static void main(String[] args) {
        //UserDAO USER_DAO = new UserDAOImpl();
        //boolean test = USER_DAO.auth("rekish", "test");
        String password = "admin";
        String passw = "password('" + password + "')";
        System.out.println(passw);

    }*/
}
