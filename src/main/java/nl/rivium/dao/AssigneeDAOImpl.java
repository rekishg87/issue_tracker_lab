package nl.rivium.dao;

import nl.rivium.entities.Assignee;
import javax.ejb.EJBException;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Retrieve data from the Assignee table in the database
 */
public class AssigneeDAOImpl implements AssigneeDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     *
     * @return all the possible options from the assignee table as a List<>
     */
    @Override
    public List<Assignee> getAssigneeList(){
        List<Assignee> assigneeList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT a FROM Assignee a");
            manager.getTransaction().commit();
            assigneeList = query.getResultList();

        } catch (EJBException ex) {
            ex.printStackTrace();
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return assigneeList;
    }
}
