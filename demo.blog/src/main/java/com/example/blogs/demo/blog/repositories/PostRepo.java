package com.example.blogs.demo.blog.repositories;

import java.util.List;

import com.example.blogs.demo.blog.entities.Category;
import com.example.blogs.demo.blog.entities.Post;
import com.example.blogs.demo.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
	

}
