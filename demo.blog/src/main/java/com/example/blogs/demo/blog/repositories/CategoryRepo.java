package com.example.blogs.demo.blog.repositories;

import com.example.blogs.demo.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
