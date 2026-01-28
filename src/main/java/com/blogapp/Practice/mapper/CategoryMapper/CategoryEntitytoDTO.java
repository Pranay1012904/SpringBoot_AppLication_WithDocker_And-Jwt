package com.blogapp.Practice.mapper.CategoryMapper;

import com.blogapp.Practice.dto.CategoryDto;
import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Category;
import com.blogapp.Practice.mapper.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, componentModel = "spring", uses = CategoryDto.class)
public interface CategoryEntitytoDTO {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryDto entityToDTO(Category category);
}
