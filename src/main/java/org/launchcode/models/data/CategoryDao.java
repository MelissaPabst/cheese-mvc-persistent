package org.launchcode.models.data;


import org.launchcode.models.Category;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
