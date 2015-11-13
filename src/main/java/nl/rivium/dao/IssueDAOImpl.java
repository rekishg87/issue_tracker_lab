package nl.rivium.dao;

import nl.rivium.entities.Issue;
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
            Query query = manager.createQuery
                    ("SELECT i FROM Issue i");

            listIssues = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return listIssues;
    }

    @Override
    public List<Issue> createIssue(String description) {

        try{
            manager.getTransaction().begin();
            Issue issue = new Issue();
            issue.setDescription(description);
            manager.persist(issue);

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return null;
    }
}
