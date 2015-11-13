package nl.rivium.dao;

import nl.rivium.entities.Assignee;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public class AssigneeDAOImpl implements AssigneeDAO{
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Assignee> getAssigneeList() {
        List<Assignee> assigneeList = null;

        try{
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT a FROM Assignee a");

            assigneeList = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return assigneeList;
    }
}
