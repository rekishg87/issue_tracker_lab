package nl.rivium.dao;

/**
 * Created by Rekish on 1/19/2016.
 * Interface for the Roles class
 */
public interface RolesDAO {
    /**
     * @param username for the user that is being logged in.
     * @return the userRole id, for which group the user has access to.
     */
    int userAccess (String username);
}
