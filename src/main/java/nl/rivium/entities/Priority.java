package nl.rivium.entities;

import javax.persistence.*;

/**
 * Created by Rekish on 11/13/2015.
 * JPA Entity class for the Priority table
 */

@Entity
@Table(name = "priority")
public class Priority {
    private int id;
    private String name;

    // Empty Constructor
    public Priority() {

    }

    // Constructor
    public Priority(int id, String name) {
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
