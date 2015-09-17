package nl.rivium.entities;

import javax.persistence.*;
import javax.xml.registry.infomodel.EmailAddress;

/**
 * Created by Rekish on 9/15/2015.
 */

@Entity
@Table(name = "users")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;

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

    @Column(name = "PASSWORD")
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

    public String toString() {
        return "results: " + getUsername() + ", " + getPassword() + ", ";
    }


}
