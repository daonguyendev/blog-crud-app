package com.daonguyen.blogcrudapp.repository;

import com.daonguyen.blogcrudapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findByName(String name);

    void delete(String id);
}
