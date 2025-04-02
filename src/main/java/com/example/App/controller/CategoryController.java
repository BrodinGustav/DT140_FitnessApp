package com.example.App.controller;

import com.example.App.model.Category;
import com.example.App.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
