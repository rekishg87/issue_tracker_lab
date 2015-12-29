package nl.rivium.dao;

import nl.rivium.entities.Assignee;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Interface for the Assignee class
 */

public interface AssigneeDAO {
    /**
     * Method to retrieve all assignee's from the database as a List<>
     */
    List<Assignee> getAssigneeList();
}
