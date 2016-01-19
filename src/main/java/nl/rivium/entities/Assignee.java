package nl.rivium.entities;

import javax.persistence.*;

/**
 * Created by Rekish on 11/13/2015.
 * JPA Entity class for the Assignee table
 */

@Entity
@Table(name = "assignee")
public class Assignee {
    private int id;
    private String name;

    public Assignee() {

    }

    public Assignee (int id, String name) {
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
