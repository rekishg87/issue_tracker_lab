package nl.rivium.dao;

import nl.rivium.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Rekish on 10/5/2015.
 * Retrieve data from the Issue table in the database
 */
public class IssueDAOImpl implements IssueDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssueDAOImpl.class);
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private static final String EXCEPTION_STRING = "Exception Occurred";

    /**
     * @return all the issues that are not resolved from the database as a List<>
     */
    @Override
    public List<Issue> getAllIssuesList() {
        LOGGER.info("Getting all open issues.");
        List<Issue> issueTable;
        List<Priority> priorityTable;
        List<Assignee> assigneeTable;
        List<Category> categoryTable;
        List<Status> statusTable;
        List tables = new ArrayList<Issue>();

        try {
            manager.getTransaction().begin();

            Query issueTableQuery = manager.createQuery
                    ("SELECT i FROM Issue i where i.statusId != 3");
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
            LOGGER.error(EXCEPTION_STRING, exception);
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
        LOGGER.info("Creating a new issue.");
        Date currentDate = new Date();
        final int notAssigned = 1;
        final int registered = 1;

        try {
            manager.getTransaction().begin();
            Issue issue = new Issue();
            issue.setSubject(subject);
            issue.setDescription(description);
            issue.setCategoryId(categoryId);
            // Default 1 for not assigned, because an new issue is not yet assigned to an assignee
            issue.setAssigneeId(notAssigned);
            issue.setPriorityId(priorityId);
            // Default for Registered.
            issue.setStatusId(registered);
            issue.setCreatedBy(username);
            issue.setIssueCreatedOn(currentDate);
            manager.persist(issue);
            manager.getTransaction().commit();
        } catch (IllegalStateException | RollbackException exception) {
            LOGGER.error(EXCEPTION_STRING, exception);
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param id to be searched for in the database.
     * @return the issueId that needs to be updated or removed.
     */

    @Override
    public Issue findIssue(int id) {
        Issue foundIssue = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT i from Issue i where i.id = :id");
            query.setParameter("id", id);
            foundIssue = (Issue) query.getSingleResult();
        } catch (IllegalStateException exception) {
            LOGGER.error(EXCEPTION_STRING, exception);
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

    /**
     *
     * @param id of the issue to be updated.
     * @param priorityId of the issue to be updated.
     * @param subject of the issue to be updated.
     * @param description of the issue to be updated.
     * @param assigneeId of the issue to be updated.
     * @param categoryId of the issue to be updated.
     * @param statusId of the issue to be updated.
     * @return nothing, data is only being updated.
     */

    @Override
    public List<Issue> updateIssue(int id, int priorityId, String subject, String description,
                                   int assigneeId, int categoryId, int statusId) {
        LOGGER.info("Updating an existing issue.");
        Issue selectedIssue = findIssue(id);

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
                    LOGGER.error(EXCEPTION_STRING, exception);
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<Issue> getResolvedIssues() {
        LOGGER.info("Getting all resolved issues.");
        List<Issue> issueTable;
        List<Priority> priorityTable;
        List<Assignee> assigneeTable;
        List<Category> categoryTable;
        List<Status> statusTable;
        List tables = new ArrayList<Issue>();

        try {
            manager.getTransaction().begin();

            Query issueTableQuery = manager.createQuery
                    ("SELECT i FROM Issue i where i.statusId = 3");
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
            LOGGER.error(EXCEPTION_STRING, exception);
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
     *
     * @param id of the issue to be removed.
     * @return nothing, issue is being removed.
     */

    @Override
    public List<Issue> removeIssue(int id) {
        LOGGER.info("Removing an existing issue.");
        Issue selectedIssue = findIssue(id);

        try {
            manager.getTransaction().begin();
            manager.remove(manager.contains(selectedIssue) ? selectedIssue : manager.merge(selectedIssue));
            manager.getTransaction().commit();
        } catch (IllegalArgumentException | TransactionRequiredException |
                IllegalStateException | RollbackException exception) {
                    LOGGER.error(EXCEPTION_STRING, exception);
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }
        return Collections.emptyList();
    }
}
