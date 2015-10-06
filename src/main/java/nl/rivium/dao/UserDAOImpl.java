package nl.rivium.dao;

import nl.rivium.entities.Issue;
import nl.rivium.entities.User;
import org.hibernate.HibernateException;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rekish on 9/17/2015.
 */

public class UserDAOImpl implements UserDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private EntityTransaction transaction = manager.getTransaction();

    @Override
    public boolean auth(String username, String password) {
        boolean found = false;

        try{
            manager.getTransaction().begin();
            User user = new User();
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());

            Query query = manager.createQuery
                    ("SELECT u FROM User u WHERE " +
                            "u.username = :username and u.password = :password");

            query.setParameter("username", username);
            query.setParameter("password", password);

            List<Issue> listIssues = query.getResultList();
            if(listIssues.isEmpty()) {
                found = false;
            } else {
                found = true;
            }

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return found;
    }

    @Override
    public JsonArray value(String username, String password) {
        JsonArray value = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("username", username)
                        .add("password", password))
                .build();
        System.out.println(value);
        return value;
    }

    @Override
    public List<User> signupUser (String username, String password, String email) throws HibernateException {

        List<User> userAdded = new ArrayList<>();

        try{
            transaction.begin();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
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
