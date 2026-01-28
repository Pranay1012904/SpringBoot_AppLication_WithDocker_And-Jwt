package com.blogapp.Practice.service;

import com.blogapp.Practice.dto.CategoryDto;
import com.blogapp.Practice.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto category);

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(CategoryDto updateCategory, Long id);

    String deleteCategory(Long id);
}
