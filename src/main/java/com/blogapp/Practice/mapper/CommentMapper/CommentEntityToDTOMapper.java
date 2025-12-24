package com.blogapp.Practice.mapper.CommentMapper;


import com.blogapp.Practice.dto.CommentDto;
import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Comment;
import com.blogapp.Practice.mapper.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, componentModel = "spring", uses = CommentDto.class)
public interface CommentEntityToDTOMapper {
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "body", target = "body")
    CommentDto entityToDTO(Comment comment);
}
