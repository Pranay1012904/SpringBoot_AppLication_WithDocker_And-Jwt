package com.blogapp.Practice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
//@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false,unique = false)
    String title;
    @Column(nullable = false,unique = false)
    String description;
    @Column(nullable = false,unique = false)
    String content;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Comment> commentSet=new HashSet<>();
}
