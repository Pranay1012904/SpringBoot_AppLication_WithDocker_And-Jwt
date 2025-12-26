package com.blogapp.Practice.repository;

import com.blogapp.Practice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByPostId(Long PostId);
}
