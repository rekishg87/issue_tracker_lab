package nl.rivium.dao;

/**
 * Created by Rekish on 9/17/2015.
 */
public interface UserDAO {
    boolean auth (String username, String password);
}
