package com.blogapp.Practice.service.impl;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Post;
import com.blogapp.Practice.exception.ResourceNotFoundException;
import com.blogapp.Practice.mapper.PostMapper.PostEntityToDTO;
import com.blogapp.Practice.repository.PostRepository;
import com.blogapp.Practice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<PostDto> getAllPosts() {

        List<Post> allPosts = postRepository.findAll();
        List<PostDto> allPostDto = allPosts.stream()
                .map(postEntityToDTO::postEntityToDTO) //p -> postEntityToDTO.postEntityToDTO(p))
                .toList();
        return allPostDto;
    }

    @Override
    public PostDto getPostById(Long id) {
       Post fetchedPost= postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("POST WITH ID %d NOT FOUND !",id)));
       return postEntityToDTO.postEntityToDTO(fetchedPost);
    }
}
