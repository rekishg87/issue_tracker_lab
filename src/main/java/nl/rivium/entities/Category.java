package nl.rivium.entities;

import javax.persistence.*;

/**
 * Created by Rekish on 11/13/2015.
 * JPA Entity class for the Category table
 */

@Entity
@Table(name = "category")
public class Category {
    private int id;
    private String name;

    // Empty Constructor
    public Category() {

    }

    // Constructor
    public Category(int id, String name) {
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
