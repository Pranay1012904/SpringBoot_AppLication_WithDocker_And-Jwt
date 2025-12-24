package com.blogapp.Practice.controller;

import com.blogapp.Practice.dto.PostDto;
import com.blogapp.Practice.entity.Post;
import com.blogapp.Practice.mapper.PostMapper.PostDTOToEntity;
import com.blogapp.Practice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> allPosts = postService.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/getPostById/rp")
    public ResponseEntity<PostDto> getPostById(@RequestParam Long id) {
        PostDto fetchedPost = postService.getPostById(id);
        return new ResponseEntity<>(fetchedPost, HttpStatus.FOUND);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Long id) {
        PostDto updatedPost = postService.updatePostById(postDto, id);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }


}
