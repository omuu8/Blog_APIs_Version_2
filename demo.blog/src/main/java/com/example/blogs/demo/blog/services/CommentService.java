package com.example.blogs.demo.blog.services;


import com.example.blogs.demo.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

}
