package nl.rivium.dao;

import nl.rivium.entities.Category;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Retrieve data from the Category table in the database
 */
public class CategoryDAOImpl implements CategoryDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    /**
     *
     * @return all the possible options from the Category table as a List<>
     */
    @Override
    public List<Category> getCategoryList(){
        List<Category> categoryList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT c FROM Category c");
            manager.getTransaction().commit();
            categoryList = query.getResultList();


        } catch (EJBException ex) {
            ex.printStackTrace();
        } finally {
            if (manager.getTransaction().isActive()){
                manager.getTransaction().rollback();
                manager.close();
                factory.close();
            }
        }

        return categoryList;
    }
}
