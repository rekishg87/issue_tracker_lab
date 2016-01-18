package nl.rivium.dao;

import nl.rivium.entities.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rekish on 10/5/2015.
 * Retrieve data from the Issue table in the database
 */
public class IssueDAOImpl implements IssueDAO {
    private static final Logger logger = LoggerFactory.getLogger(IssueDAOImpl.class);
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     * @return all the issues registered in the database as a List<>
     */
    @Override
    public List<Issue> getAllIssuesList() {
        List<Issue> issueTable;
        List<Issue> priorityTable;
        List<Issue> assigneeTable;
        List<Issue> categoryTable;
        List<Issue> statusTable;
        ArrayList tables = new ArrayList<>();
        logger.info("Getting all Issues API Call started");

        try {
            manager.getTransaction().begin();

            Query issueTableQuery = manager.createQuery
                    ("SELECT i FROM Issue i");
            Query priorityTableQuery = manager.createQuery
                    ("SELECT p FROM Priority p");
            Query assigneeTableQuery = manager.createQuery
                    ("SELECT a FROM Assignee a");
            Query categoryTableQuery = manager.createQuery
                    ("SELECT c FROM Category c");
            Query statusTableQuery = manager.createQuery
                    ("SELECT s FROM Status s");

            manager.getTransaction().commit();

            issueTable = issueTableQuery.getResultList();
            priorityTable = priorityTableQuery.getResultList();
            assigneeTable = assigneeTableQuery.getResultList();
            categoryTable = categoryTableQuery.getResultList();
            statusTable = statusTableQuery.getResultList();

            tables.add(issueTable);
            tables.add(priorityTable);
            tables.add(assigneeTable);
            tables.add(categoryTable);
            tables.add(statusTable);

        } catch (IllegalStateException | RollbackException exception) {
            logger.error(exception.getMessage());
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return tables;
    }

    /**
     * @param description of a new issue
     * @param subject     of a new issue
     * @param categoryId  of a new issue
     * @param priorityId  of a new issue
     * @return nothing because nothing is being retrieved.
     */


    @Override
    public List<Issue> createIssue(String description, String subject, int categoryId, int priorityId, String username) {
        Date currentDate = new Date();

        try {
            manager.getTransaction().begin();
            Issue issue = new Issue();
            issue.setSubject(subject);
            issue.setDescription(description);
            issue.setCategoryId(categoryId);
            issue.setAssigneeId(1); // Default 1 for not assigned, because an new issue is not yet assigned to an assignee
            issue.setPriorityId(priorityId);
            issue.setStatusId(1); // Default for Registered.
            issue.setCreatedBy(username);
            issue.setIssueCreatedOn(currentDate);
            manager.persist(issue);
            manager.getTransaction().commit();

        } catch (IllegalStateException | RollbackException exception) {
            logger.error(exception.getMessage());
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }
        return null;
    }

    @Override
    public Issue findIssue(int id) {
        Issue foundIssue = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT i from Issue i where i.id = :id");
            query.setParameter("id", id);
            foundIssue = (Issue) query.getSingleResult();
            logger.info("findIssue method: " + foundIssue);
        } catch (IllegalStateException exception) {
            logger.error(exception.getMessage());
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                // Do not close manager and factory, it will be closed in the updateIssue method,
                // because otherwise when the updateIssue is executed
                // the manager and factory will be closed by the findIssue method
                // and a Exception will be thrown, that the manager and factory is closed.
            }
        }
        return foundIssue;
    }

    @Override
    public List<Issue> updateIssue(int id, int priorityId, String subject, String description, int assigneeId, int categoryId, int statusId) {
        Issue selectedIssue = findIssue(id);
        logger.info("updateIssue method: " + selectedIssue);
        try {
            manager.getTransaction().begin();
            selectedIssue.setPriorityId(priorityId);
            selectedIssue.setSubject(subject);
            selectedIssue.setDescription(description);
            selectedIssue.setAssigneeId(assigneeId);
            selectedIssue.setCategoryId(categoryId);
            selectedIssue.setStatusId(statusId);
            manager.merge(selectedIssue);
            manager.getTransaction().commit();
        } catch (IllegalArgumentException | TransactionRequiredException |
                IllegalStateException | RollbackException exception) {
                    logger.error(exception.getMessage());
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }
        return null;
    }

    @Override
    public List<Issue> removeIssue(int id) {
        Issue selectedIssue = findIssue(id);
        logger.info("removeIssue method: " + selectedIssue);
        try {
            manager.getTransaction().begin();
            manager.remove(manager.contains(selectedIssue) ? selectedIssue : manager.merge(selectedIssue));
            manager.getTransaction().commit();
        } catch (IllegalArgumentException | TransactionRequiredException |
                IllegalStateException | RollbackException exception) {
                    logger.error(exception.getMessage());
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }
        return null;
    }
}
