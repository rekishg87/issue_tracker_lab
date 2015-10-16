package nl.rivium.dao;

import nl.rivium.entities.Issue;
import nl.rivium.entities.User;
import org.hibernate.HibernateException;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rekish on 9/17/2015.
 */

public class UserDAOImpl implements UserDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private EntityTransaction transaction = manager.getTransaction();

    @Context
    private static HttpServletRequest request;

    @Override
    public boolean auth(String username, String password) {
        boolean found = false;
        List<String> listUsers = null;

        try{
            manager.getTransaction().begin();

            Query query = manager.createQuery
                    ("SELECT u.password FROM User u where u.username = :username");

            query.setParameter("username", username);

            listUsers = query.getResultList();

            String uncheckedPass = listUsers.get(0);

            if(BCrypt.checkpw(password, uncheckedPass)) {
                found = true;
            } else {
                found = false;
            }

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return found;
    }


    @Override
    public List<User> signupUser (String username, String password, String email) throws HibernateException {

        List<User> userAdded = new ArrayList<>();

        try{
            transaction.begin();
            User user = new User();
            user.setUsername(username);
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            user.setEmail(email);
            manager.persist(user);
            userAdded.add(user);
            transaction.commit();

            /*Query query = manager.createQuery
                    ("SELECT u FROM User u WHERE " +
                            "u.username = :username and u.password = :password");

            query.setParameter("username", username);
            query.setParameter("password", password);

            List<Issue> listIssues = query.getResultList();*/

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if(transaction.isActive()) {
                transaction.rollback();
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
