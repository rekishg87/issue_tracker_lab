package nl.rivium.entities;

import javax.persistence.*;

/**
 * Created by Rekish on 1/19/2016.
 * JPA Entity class for the Roles table
 */

@Entity
@Table(name = "roles")
public class Roles {
    private int id;
    private String name;

    // Empty Constructor
    public Roles() {

    }

    // Constructor
    public Roles(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
