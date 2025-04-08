package com.example.App.controller;

import com.example.App.model.Category;
import com.example.App.repository.CategoryRepository;
import com.example.App.response.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/create")

    @Operation(summary = "Skapar en ny kategori", description = "Lägger till en ny kategori i databasen.")
    @ApiResponse(responseCode = "201", description = "Kategori skapades", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "500", description = "Internt serverfel")

    public ResponseEntity<SuccessResponse<Category>> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryRepository.save(category);
        SuccessResponse<Category> response = new SuccessResponse<>("Kategori har skapats", createdCategory);
        return ResponseEntity.ok(response); // Skickar HTTP 200 OK + objektet
    }

    @GetMapping("/{id}")

    @Operation(summary = "Hämtar specifik kategori", description = "Hämtar specifik kategori baserat på id.")
    @ApiResponse(responseCode = "201", description = "Kategori hämtad", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Kategori hittades inte")

    public ResponseEntity<SuccessResponse<Category>> getCategoryById(@PathVariable Integer id) {
        Category category = categoryRepository.getCategoryById(id);
        SuccessResponse<Category> response = new SuccessResponse<>("Kategori med ID " + id + " har hämtats", category);
        return ResponseEntity.ok(response);
    }

    @GetMapping

    @Operation(summary = "Hämtar kategori", description = "Hämtar kategori från databasen.")
    @ApiResponse(responseCode = "201", description = "Kategori hämtade", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Kategori hittades inte")

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PutMapping("/{id}")

    @Operation(summary = "Uppdaterar aktivitet", description = "Uppdaterar aktivitet i databasen.")
    @ApiResponse(responseCode = "201", description = "Aktivitet uppdaterad", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Aktivitet hittades inte")

    public ResponseEntity<SuccessResponse<Category>> updateCategory(@PathVariable int id, @RequestBody Category category) {
        category.setId(id);
        categoryRepository.save(category);
        SuccessResponse<Category> response = new SuccessResponse<>("Kategori med ID " + id + " har uppdaterats", category);
        return ResponseEntity.ok(response); // Skickar HTTP 200 OK + objektet
    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Raderar kategori", description = "Raderar kategori från databasen.")
    @ApiResponse(responseCode = "201", description = "Kategori raderad", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(responseCode = "404", description = "Kategori hittades inte")

    public ResponseEntity<SuccessResponse<Category>> deleteCategory(@PathVariable int id, @RequestBody Category category) {
        category.setId(id);
        categoryRepository.delete(category);
        SuccessResponse<Category> response = new SuccessResponse<>("Kategori med ID " + id + " har raderats.");
        return ResponseEntity.ok(response); // Skickar HTTP 200 OK + objektet
    }
}
