package com.blogapp.Practice.service.impl;

import com.blogapp.Practice.dto.CategoryDto;
import com.blogapp.Practice.entity.Category;
import com.blogapp.Practice.exception.ResourceNotFoundException;
import com.blogapp.Practice.mapper.CategoryMapper.CategoryDTOToEntity;
import com.blogapp.Practice.mapper.CategoryMapper.CategoryEntitytoDTO;
import com.blogapp.Practice.repository.CategoryRepository;
import com.blogapp.Practice.service.CategoryService;
import com.blogapp.Practice.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryEntitytoDTO categoryEntitytoDTO;
    private CategoryDTOToEntity categoryDTOToEntity;
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto category) {
        Category toAddCategory = categoryDTOToEntity.DTOTOEntity(category);
        Category addedCategory = categoryRepository.save(toAddCategory);
        return categoryEntitytoDTO.entityToDTO(addedCategory);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category fetchedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("CATEGORY WHIT ID %d NOT FOUND!", id)));
        return categoryEntitytoDTO.entityToDTO(fetchedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> fetchedCategoryList = categoryRepository.findAll();
        return fetchedCategoryList.stream().map(e -> categoryEntitytoDTO.entityToDTO(e)).toList();

    }

    @Override
    public CategoryDto updateCategory(CategoryDto updateCategory, Long id) {
        Category fetchedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("CATEGORY WITH ID %d NOT FOUND", id)));
        fetchedCategory.setDescription(updateCategory.getDescription());
        fetchedCategory.setName(updateCategory.getName());
        fetchedCategory.setId(id);
        return categoryEntitytoDTO.entityToDTO(categoryRepository.save(fetchedCategory));
    }

    @Override
    public String deleteCategory(Long id) {
        Category fetchedCaqtegory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("NO CATERGORY WITH ID %d FOUND!")));
        categoryRepository.delete(fetchedCaqtegory);
        return Constants.DELETE_MESSAGE;
    }
}
