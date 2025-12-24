package com.blogapp.Practice.service;

import com.blogapp.Practice.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto comment);
}
