package nl.rivium.dao;

import nl.rivium.entities.Issue;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Interface for the Issue class
 */



public interface IssueDAO {
    /**
     * Method to retrieve all issues from the database as a List<>
     */
    List<Issue> getAllIssuesList();

    /**
     *
     * @param description of a new issue
     * @param subject of a new issue
     * @param categoryId of a new issue
     * @param priorityId of a new issue
     * @param username of the user that is logged in and created the issue.
     * @return nothing because nothing is being retrieved.
     */
    List<Issue> createIssue(String description, String subject, int categoryId, int priorityId, String username);

    /**
     *
     * @param id to be searched for in the database.
     * @return the issueId that needs to be updated or removed.
     */
    Issue findIssue(int id);

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
    List<Issue> updateIssue(int id, int priorityId, String subject, String description, int assigneeId, int categoryId, int statusId);

    /**
     *
     * @param id of the issue to be removed.
     * @return nothing, issue is being removed.
     */
    List<Issue> removeIssue(int id);

}
