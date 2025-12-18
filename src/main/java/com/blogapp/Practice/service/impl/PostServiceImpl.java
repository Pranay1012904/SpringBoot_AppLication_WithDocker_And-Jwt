package com.blogapp.Practice.service.impl;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Post;
import com.blogapp.Practice.mapper.PostMapper.PostDTOToEntity;
import com.blogapp.Practice.mapper.PostMapper.PostEntityToDTO;
import com.blogapp.Practice.repository.PostRepository;
import com.blogapp.Practice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    //PostDTOToEntity postDTOToEntity;
    PostEntityToDTO postEntityToDTO;

    @Override
    public PostDto createPost(Post post) {
        Post savedPost = postRepository.save(post);
        return postEntityToDTO.postEntityToDTO(savedPost);

    }
}
