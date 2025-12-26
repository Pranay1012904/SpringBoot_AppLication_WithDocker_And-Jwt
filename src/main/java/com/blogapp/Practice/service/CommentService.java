package com.blogapp.Practice.service;

import com.blogapp.Practice.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto comment);
    List<CommentDto> getAllComentsByPostId(Long postId);
    CommentDto getCommentById(Long commentId);
}
