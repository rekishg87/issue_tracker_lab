package nl.rivium.dao;

import nl.rivium.entities.Issue;

import java.util.List;

/**
 * Created by Rekish on 10/5/2015.
 */
public interface IssueDAO {

    List<Issue> allIssuesList();
    List<Issue> createIssue(String description, String subject, int categoryId, int priorityId);
}
