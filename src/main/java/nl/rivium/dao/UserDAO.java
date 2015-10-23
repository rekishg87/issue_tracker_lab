package nl.rivium.dao;

import nl.rivium.entities.Issue;
import nl.rivium.entities.User;
import org.hibernate.HibernateException;

import javax.json.JsonArray;
import java.util.List;

/**
 * Created by Rekish on 9/17/2015.
 */
public interface UserDAO {
    boolean auth (String username, String password);
    List<User> signupUser (String username, String password, String email) throws HibernateException;
}
