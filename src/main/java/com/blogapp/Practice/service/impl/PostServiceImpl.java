package com.blogapp.Practice.service.impl;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Category;
import com.blogapp.Practice.entity.Post;
import com.blogapp.Practice.exception.ResourceNotFoundException;
import com.blogapp.Practice.mapper.PostMapper.PostDTOToEntity;
import com.blogapp.Practice.mapper.PostMapper.PostEntityToDTO;
import com.blogapp.Practice.repository.CategoryRepository;
import com.blogapp.Practice.repository.PostRepository;
import com.blogapp.Practice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    //PostDTOToEntity postDTOToEntity;
    private PostEntityToDTO postEntityToDTO;
    private PostDTOToEntity postDTOToEntity;
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto post) {
        Post postMod = postDTOToEntity.postDTOToEntity(post);
        Category fetchedCategory = categoryRepository.findById(post.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(String.format("NO CATEGORY WITH ID %d FOUND", post.getCategoryId())));
        postMod.setCategory(fetchedCategory);
        Post savedPost = postRepository.save(postMod);
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
        Post fetchedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("POST WITH ID %d NOT FOUND !", id)));
        return postEntityToDTO.postEntityToDTO(fetchedPost);
    }

    @Override
    public PostDto updatePostById(PostDto postDTO, Long id) {
        Post fetchedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("POST WITH ID %d NOT FOUND!", id)));
        fetchedPost.setContent(postDTO.getContent());
        fetchedPost.setDescription(postDTO.getDescription());
        fetchedPost.setTitle(postDTO.getTitle());
        Post updatedPost = postRepository.save(fetchedPost);
        return postEntityToDTO.postEntityToDTO(updatedPost);
    }

    @Override
    public List<PostDto> findPostByCategoryId(Long id) {
        List<Post> fetchedPosts = postRepository.findByCategoryId(id);
        return fetchedPosts.stream().map(p->postEntityToDTO.postEntityToDTO(p)).toList();
    }
}
