package nl.rivium.dao;

import nl.rivium.entities.Category;

import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 */
public interface CategoryDAO {
    List<Category> getCategoryList();
}
