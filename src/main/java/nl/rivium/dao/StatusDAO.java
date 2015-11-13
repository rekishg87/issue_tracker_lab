package nl.rivium.dao;

import nl.rivium.entities.Status;

import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public interface StatusDAO {
    List<Status> getStatusList();
}
