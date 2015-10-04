package nl.rivium.dao;

import javax.json.JsonArray;

/**
 * Created by Rekish on 9/17/2015.
 */
public interface UserDAO {
    boolean auth (String username, String password);
    JsonArray value(String username, String password);
    JsonArray val();
}
