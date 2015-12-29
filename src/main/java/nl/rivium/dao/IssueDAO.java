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
     * @return nothing because nothing is being retrieved.
     */
    List<Issue> createIssue(String description, String subject, int categoryId, int priorityId);
}
