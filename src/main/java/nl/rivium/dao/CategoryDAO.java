package nl.rivium.dao;

import nl.rivium.entities.Category;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * Interface for the Category class
 */

public interface CategoryDAO {
    /**
     * Method to retrieve all category's from the database as a List<>
     */
    List<Category> getCategoryList();
}
