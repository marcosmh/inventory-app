package com.markcode.inventory.app.service;

import com.markcode.inventory.app.model.Category;
import com.markcode.inventory.app.reponse.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    ResponseEntity<CategoryResponseRest> search();

    ResponseEntity<CategoryResponseRest> searchById(Long id);

    ResponseEntity<CategoryResponseRest> save(Category category);

    ResponseEntity<CategoryResponseRest> update(Category category, Long id);

}
