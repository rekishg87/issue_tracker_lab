package nl.rivium.dao;

import nl.rivium.entities.Issue;
import nl.rivium.entities.User;
import org.hibernate.HibernateException;

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

    /*public static void main(String[] args) {
        //UserDAO USER_DAO = new UserDAOImpl();
        //boolean test = USER_DAO.auth("rekish", "test");
        String password = "admin";
        String passw = "password('" + password + "')";
        System.out.println(passw);

    }*/
}
