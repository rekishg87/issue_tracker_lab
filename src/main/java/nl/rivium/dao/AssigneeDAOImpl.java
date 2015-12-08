package nl.rivium.dao;

import nl.rivium.entities.Assignee;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public class AssigneeDAOImpl implements AssigneeDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Assignee> getAssigneeList() throws IllegalStateException {
        List<Assignee> assigneeList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT a FROM Assignee a");

            assigneeList = query.getResultList();


        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return assigneeList;
    }
}
