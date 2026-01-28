package com.blogapp.Practice.mapper.CategoryMapper;

import com.blogapp.Practice.dto.CategoryDto;
import com.blogapp.Practice.entity.Category;
import com.blogapp.Practice.mapper.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, componentModel = "spring")
public interface CategoryDTOToEntity {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category DTOTOEntity(CategoryDto categoryDto);
}
