package com.daonguyen.blogcrudapp.api;

import com.daonguyen.blogcrudapp.model.Category;
import com.daonguyen.blogcrudapp.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryApiController {

    public static final Logger logger = LoggerFactory.getLogger(CategoryApiController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories/")
    public ResponseEntity<List<Category>> listAllcategories() {
        List<Category> categories = categoryService.findAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    //    @GetMapping("/categories")
//    public Page<Category> getCategories(Pageable pageable) {
//        return categoryRepository.findAll(pageable);
//    }
//
//    @PostMapping("/categories")
//    public Category createCategory(@Valid @RequestBody Category category) {
//        return categoryRepository.save(category);
//    }
//
//    @PutMapping("categories/{categoryId}")
//    public Category updateCategory(@PathVariable String categoryId,
//                                   @Valid @RequestBody Category categoryRequest) {
//        return categoryRepository.findById(categoryId)
//                .map(category -> {
//                    category.setCategoryName(categoryRequest.getCategoryName());
//                    category.setCreatedAt(categoryRequest.getCreatedAt());
//                    category.setUpdatedAt(categoryRequest.getUpdatedAt());
//                    category.setTopics(categoryRequest.getTopics());
//                    return categoryRepository.save(category);
//                }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
//    }
//
//    @DeleteMapping("/categories/{categoryId}")
//    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
//        return categoryRepository.findById(categoryId)
//                .map(category -> {
//                    categoryRepository.delete(category);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
//    }
}
