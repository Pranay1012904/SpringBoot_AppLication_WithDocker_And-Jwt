package com.blogapp.Practice.mapper.PostMapper;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Post;
import com.blogapp.Practice.mapper.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, componentModel = "spring")
public interface PostDTOToEntity {
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "content", target = "content")
    Post postDTOToEntity(PostDto dto);
}
