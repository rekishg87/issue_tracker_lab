package nl.rivium.dao;

import nl.rivium.entities.Issue;
import nl.rivium.entities.User;
import org.hibernate.HibernateException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 9/17/2015.
 */
public class UserDAOImpl implements UserDAO {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

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
    public List<User> signupUser (String username, String password, String email) {
        try{
            manager.getTransaction().begin();
            User user = new User();
            user.setUsername(user.getUsername());
            user.setEmail(user.getEmail());
            user.setPassword(user.getPassword());
            manager.persist(user);
            System.out.println(user.toString());
            /*Query query = manager.createQuery
                    ("SELECT u FROM User u WHERE " +
                            "u.username = :username and u.password = :password");

            query.setParameter("username", username);
            query.setParameter("password", password);

            List<Issue> listIssues = query.getResultList();*/

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return null;
    }

    /*public static void main(String[] args) {
        //UserDAO USER_DAO = new UserDAOImpl();
        //boolean test = USER_DAO.auth("rekish", "test");
        String password = "admin";
        String passw = "password('" + password + "')";
        System.out.println(passw);

    }*/
}
