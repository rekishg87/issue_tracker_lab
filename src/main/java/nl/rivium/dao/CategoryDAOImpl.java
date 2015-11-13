package nl.rivium.dao;

import nl.rivium.entities.Category;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public class CategoryDAOImpl implements CategoryDAO{
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = null;

        try{
            manager.getTransaction().begin();
            Query query = manager.createQuery
                    ("SELECT c FROM Category c");

            categoryList = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }

        return categoryList;
    }
}
