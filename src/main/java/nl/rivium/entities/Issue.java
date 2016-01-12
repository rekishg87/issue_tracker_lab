package nl.rivium.entities;

import javax.persistence.*;
import java.sql.Blob;


/**
 * Created by Rekish on 9/14/2015.
 * JPA Entity class for the Issues table
 */

@Entity
@Table(name = "issues")
public class Issue {
    private int id;
    private int categoryId;
    private String subject;
    private String description;
    private int statusId;
    private int priorityId;
    private int assigneeId;
    private Blob screenshot;

    // No argument Constructor
    public Issue() {

    }

    // Constructor
    public Issue(int id, int categoryId, String subject, String description,
                 int statusId, int priorityId, int assigneeId, Blob screenshot) {
        this.id = id;
        this.categoryId = categoryId;
        this.subject = subject;
        this.description = description;
        this.statusId = statusId;
        this.priorityId = priorityId;
        this.assigneeId = assigneeId;
        this.screenshot = screenshot;
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

    @Column(name = "CATEGORY_ID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "SUBJECT")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    @Basic(fetch = FetchType.LAZY)
    @Lob @Column(name="SCREENSHOT")
    public Blob getScreenshot()  {
        return screenshot;
    }

    public void setScreenshot(Blob screenshot) {
        this.screenshot = screenshot;
    }
}
