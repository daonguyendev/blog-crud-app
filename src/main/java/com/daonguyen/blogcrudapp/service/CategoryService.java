package com.daonguyen.blogcrudapp.service;

import com.daonguyen.blogcrudapp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findById(String id);

    Category findByName(String name);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(String id);

    void deleteAllCategories();

    List<Category> findAllCategories();

    boolean isCategoryExist(Category category);

}
