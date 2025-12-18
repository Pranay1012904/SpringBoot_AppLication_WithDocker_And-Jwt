package com.blogapp.Practice.controller;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Post;
import com.blogapp.Practice.mapper.PostMapper.PostDTOToEntity;
import com.blogapp.Practice.mapper.PostMapper.PostEntityToDTO;
import com.blogapp.Practice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/api")
@AllArgsConstructor
public class PostController {

    PostService postService;
    PostDTOToEntity postDTOToEntity;

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
        Post receivedPost = postDTOToEntity.postDTOToEntity(post);
        PostDto savedPost = postService.createPost(receivedPost);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }
}
