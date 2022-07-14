package com.markcode.inventory.app.reponse;

import com.markcode.inventory.app.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> category;

}
