package com.example.App.controller;

import com.example.App.model.Category;
import com.example.App.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")

        @Operation(summary = "Skapar en ny kategori", description = "Lägger till en ny kategori i databasen.")
    @ApiResponse(responseCode = "201", description = "Kategori skapades",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "500", description = "Internt serverfel")

    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Hämtar specifik kategori", description = "Hämtar specifik kategori baserat på id.")
    @ApiResponse(responseCode = "201", description = "Kategori hämtad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Kategori hittades inte")

    public Category getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping

    @Operation(summary = "Hämtar kategori", description = "Hämtar kategori från databasen.")
    @ApiResponse(responseCode = "201", description = "Kategori hämtade",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Kategori hittades inte")

    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}")

    @Operation(summary = "Uppdaterar aktivitet", description = "Uppdaterar aktivitet i databasen.")
    @ApiResponse(responseCode = "201", description = "Aktivitet uppdaterad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))   
    @ApiResponse(responseCode = "404", description = "Aktivitet hittades inte")

    public Category updateCategory(@PathVariable Integer id, @RequestBody Category categoryDetails) {
        return categoryService.updateCategory(id, categoryDetails);
    }

    @DeleteMapping("/{id}")
    
    @Operation(summary = "Raderar kategori", description = "Raderar kategori från databasen.")
    @ApiResponse(responseCode = "201", description = "Kategori raderad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Kategori hittades inte")


    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
