package com.markcode.inventory.app.dao;

import com.markcode.inventory.app.model.Category;
import com.markcode.inventory.app.reponse.CategoryResponseRest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

public interface ICategoryDao extends CrudRepository<Category, Long> {


}
