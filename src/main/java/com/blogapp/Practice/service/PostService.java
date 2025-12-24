package com.blogapp.Practice.service;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Post;

import java.util.List;

public interface PostService {

    PostDto createPost(Post post);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePostById(PostDto postDTO, Long id);

}
