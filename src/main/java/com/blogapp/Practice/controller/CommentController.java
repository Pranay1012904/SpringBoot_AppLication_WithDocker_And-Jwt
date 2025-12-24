package com.blogapp.Practice.controller;

import com.blogapp.Practice.dto.CommentDto;
import com.blogapp.Practice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment/api")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping("/create/{postID}/comment")
    public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto, @PathVariable(name = "postID") Long id) {
        CommentDto savedComment = commentService.createComment(id, commentDto);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

}
