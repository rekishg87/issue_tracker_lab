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
     * @return true or false for boolean found
     */
    boolean authenticate (String username, String password);

    /**
     *
     * @param username to be persisted in the database for the new user
     * @param password to be persisted, encrypted by JBCrypt in the database for the new user
     * @param email to be persisted in the database for the new user
     * @return the username that has been entered, to check if the username is available.
     */
    String registerUser (String username, String password, String email);
}
