package nl.rivium.dao;

import nl.rivium.entities.Priority;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Interface for the Priority class
 */
public interface PriorityDAO {
    /**
     * Method to retrieve all priority's from the database as a List<>
     */
    List<Priority> getPriorityList();
}
