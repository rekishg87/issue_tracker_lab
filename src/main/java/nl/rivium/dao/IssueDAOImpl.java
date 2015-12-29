package nl.rivium.dao;

import nl.rivium.entities.Issue;
import org.hibernate.HibernateException;

import javax.ejb.EJBException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rekish on 10/5/2015.
 * Retrieve data from the Issue table in the database
 */
public class IssueDAOImpl implements IssueDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     *
     * @return all the issues registered in the database as a List<>
     */
    @Override
    public List<Issue> getAllIssuesList() {
        List<Issue> issues;
        List<Issue> assignees;
        ArrayList allIssues = new ArrayList<>();

        try {
            manager.getTransaction().begin();
//            Query query = manager.createQuery
//                    ("SELECT i.id, p.name, s.name, a.name, i.description, c.name, i.subject " +
//                            "FROM Issue i, Priority p, Status s, Assignee a, Category c " +
//                            "WHERE a.id = i.assigneeId " +
//                            "AND c.id = i.categoryId " +
//                            "AND s.id = i.statusId " +
//                            "AND p.id = i.priorityId");

            Query issuesQuery = manager.createQuery
                    ("SELECT i FROM Issue i");
            Query assigneeQuery = manager.createQuery
                    ("SELECT a FROM Assignee a");


            manager.getTransaction().commit();
            issues = issuesQuery.getResultList();
            assignees = assigneeQuery.getResultList();
            allIssues.add(issues);
            allIssues.add(assignees);


        } catch (EJBException ex) {
            System.out.println("EJBException Catched: ");
            ex.printStackTrace();
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return allIssues;
    }

    /**
     *
     * @param description of a new issue
     * @param subject of a new issue
     * @param categoryId of a new issue
     * @param priorityId of a new issue
     * @return nothing because nothing is being retrieved.
     */
    @Override
    public List<Issue> createIssue(String description, String subject, int categoryId, int priorityId) {

        try {
            manager.getTransaction().begin();
            Issue issue = new Issue();
            issue.setSubject(subject);
            issue.setDescription(description);
            issue.setCategoryId(categoryId);
            issue.setAssigneeId(1); //For now it has a default, later when finalizing project, it will be set from the client-side
            issue.setPriorityId(priorityId);
            issue.setStatusId(1); //For now it has a default, later when finalizing project, it will be set from the client-side
            manager.persist(issue);
            manager.getTransaction().commit();

        } catch (EJBException ex) {
            ex.printStackTrace();
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return null;
    }
}
