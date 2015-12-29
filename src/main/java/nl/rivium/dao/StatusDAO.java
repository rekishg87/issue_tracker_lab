package nl.rivium.dao;

import nl.rivium.entities.Status;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Interface for the Status class
 */
public interface StatusDAO {
    /**
     * Method to retrieve all statuses from the database as a List<>
     */
    List<Status> getStatusList();
}
