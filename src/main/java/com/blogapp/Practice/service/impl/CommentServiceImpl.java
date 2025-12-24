package com.blogapp.Practice.service.impl;

import com.blogapp.Practice.dto.CommentDto;
import com.blogapp.Practice.entity.Comment;
import com.blogapp.Practice.entity.Post;
import com.blogapp.Practice.exception.ResourceNotFoundException;
import com.blogapp.Practice.mapper.CommentMapper.CommentDTOToEntityMapper;
import com.blogapp.Practice.mapper.CommentMapper.CommentEntityToDTOMapper;
import com.blogapp.Practice.repository.CommentRepository;
import com.blogapp.Practice.repository.PostRepository;
import com.blogapp.Practice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentDTOToEntityMapper commentDTOToEntityMapper;
    private CommentRepository commentRepository;
    private CommentEntityToDTOMapper commentEntityToDTOMapper;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDTO) {
        Post fetchedPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(String.format("POST WITH ID %d NOT FOUND!", postId)));

        Comment comment = commentDTOToEntityMapper.dtoToEntity(commentDTO);
        comment.setPost(fetchedPost);
        Comment savedComment = commentRepository.save(comment);

        return commentEntityToDTOMapper.entityToDTO(savedComment);
    }
}
