package com.blogapp.Practice.service;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Post;

public interface PostService {

    PostDto createPost(Post post);

}
