package nl.rivium.entities;

import javax.persistence.*;

/**
 * Created by Rekish on 9/15/2015.
 * JPA Entity class for the Users table
 */

@Entity
@Table(name = "users")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int roles_id;
    private String sessionId;

    // Empty Constructor
    public User() {

    }

    // Constructor
    public User(int id, String username, String password, String email, int roles_id, String sessionId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles_id = roles_id;
        this.sessionId = sessionId;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSW")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ROLES_ID")
    public int getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(int roles_id) {
        this.roles_id = roles_id;
    }

    @Column(name = "SESSION_ID")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
