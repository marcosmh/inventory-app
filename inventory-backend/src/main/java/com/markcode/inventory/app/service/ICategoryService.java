package com.markcode.inventory.app.service;

import com.markcode.inventory.app.reponse.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    ResponseEntity<CategoryResponseRest> search();
}
