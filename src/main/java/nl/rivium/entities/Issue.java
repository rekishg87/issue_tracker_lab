package nl.rivium.entities;

import javax.persistence.*;

/**
 * Created by Rekish on 9/14/2015.
 */

@Entity
@Table(name = "issues")
public class Issue {
    private int id;
    private int categoryId;
    private String description;
    private int statusId;
    private int priorityId;
    private int assigneeId;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CATEGORY_ID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "STATUS_ID")
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Column(name = "PRIORITY_ID")
    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    @Column(name = "ASSIGNEE_ID")
    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }
}
