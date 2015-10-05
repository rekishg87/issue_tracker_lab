package nl.rivium.dao;

import nl.rivium.entities.Issue;
import nl.rivium.entities.User;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 10/5/2015.
 */
public class IssueDAOImpl implements IssueDAO{
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Issue> allIssuesList() {
        List<Issue> listIssues = null;

        try{
            manager.getTransaction().begin();
            User user = new User();
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());

            Query query = manager.createQuery
                    ("SELECT u FROM User u");


            listIssues = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return listIssues;
    }
}
