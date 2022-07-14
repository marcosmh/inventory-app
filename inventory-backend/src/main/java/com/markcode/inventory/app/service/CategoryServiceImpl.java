package com.markcode.inventory.app.service;

import com.markcode.inventory.app.dao.ICategoryDao;
import com.markcode.inventory.app.model.Category;
import com.markcode.inventory.app.reponse.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> category = (List<Category>) categoryDao.findAll();

            if(!category.isEmpty()) {
                response.getCategoryResponse().setCategory(category);
                response.setMetadata("Respuesta OK","00","Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta KO","-1","No se encontraron Categorias");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            response.setMetadata("Respuesta KO","-1","Error al consultar");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Optional<Category> category = categoryDao.findById(id);
            if(category.isPresent()){
                list.add(category.get());
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta OK","00","Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta KO","-1","Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            response.setMetadata("Respuesta KO","-1","Error al consultar por id");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Category categorySave = categoryDao.save(category);
            if(categorySave != null) {
                list.add(categorySave);
                response.getCategoryResponse().setCategory(list);
            } else {
                response.setMetadata("Respuesta KO","-1","Error al guardar Categoria");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {
            response.setMetadata("Respuesta KO","-1","Error al guardar Categoria");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Optional<Category> categorySerch = categoryDao.findById(id);
            if(categorySerch.isPresent()) {
                categorySerch.get().setName(category.getName());
                categorySerch.get().setDescription(category.getDescription());
                Category categoryUpdate = categoryDao.save(categorySerch.get());
                if(categoryUpdate != null) {
                    list.add(categoryUpdate);
                    response.getCategoryResponse().setCategory(list);
                    response.setMetadata("Respuesta OK","00","Categoria actualizada");
                } else {
                    response.setMetadata("Respuesta KO","-1","Categoria no actualizada");
                    return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("Respuesta KO","-1","Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            response.setMetadata("Respuesta KO","-1","Error al actualizar Categoria");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            Optional<Category> categorySerch = categoryDao.findById(id);
            if(categorySerch.isPresent()) {
                categoryDao.deleteById(id);
                response.setMetadata("Respuesta OK","00","Categoria eliminada");
            } else {
                response.setMetadata("Respuesta KO","-1","Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            response.setMetadata("Respuesta KO","-1","Error al eliminar Categoria");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }
}
