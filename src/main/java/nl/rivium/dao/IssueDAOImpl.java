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
                    ("SELECT i.id, p.name, s.name, a.name, i.description, c.name " +
                            "FROM Issue i, Priority p, Status s, Assignee a, Category c " +
                            "WHERE a.id = i.assigneeId " +
                            "AND c.id = i.categoryId " +
                            "AND s.id = i.statusId " +
                            "AND p.id = i.priorityId");

            listIssues = query.getResultList();
            System.out.println("listIssues: " + listIssues);

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return listIssues;
    }

    @Override
    public List<Issue> createIssue(String description, int categoryId, int priorityId) {

        try{
            manager.getTransaction().begin();
            Issue issue = new Issue();
            issue.setDescription(description);
            issue.setCategoryId(categoryId);
            issue.setAssigneeId(1);
            issue.setPriorityId(priorityId);
            issue.setStatusId(1);
            manager.persist(issue);

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return null;
    }
}
