package nl.rivium.dao;

import nl.rivium.connection.JPAConnection;
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
    //private static EntityManager manager = JPAConnection.createEntityManager();
    EntityManager manager;

    @Override
    public List<Issue> allIssuesList() {
        List<Issue> listIssues = null;

        try{
            manager = JPAConnection.createEntityManager();
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT i.id, p.name, s.name, a.name, i.description, c.name, i.subject " +
                            "FROM Issue i, Priority p, Status s, Assignee a, Category c " +
                            "WHERE a.id = i.assigneeId " +
                            "AND c.id = i.categoryId " +
                            "AND s.id = i.statusId " +
                            "AND p.id = i.priorityId");

            listIssues = query.getResultList();
            System.out.println("listIssues: " + listIssues);
            manager.getTransaction().commit();

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

        return listIssues;
    }

    @Override
    public List<Issue> createIssue(String description, String subject, int categoryId, int priorityId) {

        try{
            manager = JPAConnection.createEntityManager();
            manager.getTransaction().begin();
            Issue issue = new Issue();
            issue.setSubject(subject);
            issue.setDescription(description);
            issue.setCategoryId(categoryId);
            issue.setAssigneeId(1);
            issue.setPriorityId(priorityId);
            issue.setStatusId(1);
            manager.persist(issue);

            manager.getTransaction().commit();

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

        return null;
    }
}
