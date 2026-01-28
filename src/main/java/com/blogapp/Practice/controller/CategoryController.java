package com.blogapp.Practice.controller;

import com.blogapp.Practice.dto.CategoryDto;
import com.blogapp.Practice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/api")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id) {
        CategoryDto fetchedCategory = categoryService.getCategory(id);
        return new ResponseEntity<>(fetchedCategory, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryDto>> findCategoryById() {
        List<CategoryDto> fetchedCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(fetchedCategories,HttpStatus.OK);
    }


    @PutMapping("/updateCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @RequestParam Long id){
      CategoryDto updatedCategory=  categoryService.updateCategory(categoryDto,id);
      return new ResponseEntity<>(updatedCategory,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam Long id){
           String message= categoryService.deleteCategory(id);
           return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
