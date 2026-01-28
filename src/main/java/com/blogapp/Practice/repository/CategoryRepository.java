package com.blogapp.Practice.repository;

import com.blogapp.Practice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
