package nl.rivium.dao;

import nl.rivium.entities.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDAOImpl.class);
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();
    private static final String EXCEPTION_STRING = "Exception Occurred";

    /**
     *
     * @return all the possible options from the Category table as a List<>
     */
    @Override
    public List<Category> getCategoryList() {
        LOGGER.info("Getting all category options.");
        List<Category> categoryList = null;

        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT c FROM Category c");
            manager.getTransaction().commit();
            categoryList = query.getResultList();
        } catch (IllegalStateException exception) {
            LOGGER.error(EXCEPTION_STRING, exception);
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
