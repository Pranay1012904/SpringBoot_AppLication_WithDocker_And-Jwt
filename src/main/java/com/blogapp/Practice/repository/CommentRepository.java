package com.blogapp.Practice.repository;

import com.blogapp.Practice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
