package org.launchcode.models.data;

import org.launchcode.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

//This dao allows us to access Menu objects via the data layer from within our controllers

@Repository
@Transactional
public interface MenuDao extends CrudRepository<Menu, Integer> {
}
