package com.example.blogs.demo.blog.repositories;

import com.example.blogs.demo.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepo  extends JpaRepository<Comment, Integer> {

}
