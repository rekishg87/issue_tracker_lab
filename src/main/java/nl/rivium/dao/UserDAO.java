package nl.rivium.dao;

/**
 * Created by Rekish on 11/13/2015.
 * Interface for the User class
 */
public interface UserDAO {
    /**
     *
     * @param username to be checked in the database
     * @param password to be decrypted by JBCrypt and checked for the right match in the database
     * @return if user has entered correct information corresponding with the database
     */
    boolean authenticate (String username, String password);

    /**
     *
     * @param username to be persisted in the database for the new user
     * @param password to be persisted, encrypted by JBCrypt in the database for the new user
     * @param email to be persisted in the database for the new user
     * @return the username as a String that has been persisted in the database for the new registered user
     */
    String registerUser (String username, String password, String email);
}
