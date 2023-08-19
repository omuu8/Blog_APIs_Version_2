package com.example.blogs.demo.blog.controllers;

import java.util.List;
import java.util.Map;

import com.example.blogs.demo.blog.payloads.ApiResponse;
import com.example.blogs.demo.blog.payloads.UserDto;
import com.example.blogs.demo.blog.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	//Logger is imp because we can change settings with it in console
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private UserService userService;

	// POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// PUT- update user

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}

	//ADMIN
	// DELETE -delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
	}

	// GET - user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	// GET - user get
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	@PostMapping("/add/")
	public ResponseEntity<?> addUserInformation(
			@RequestParam("file") MultipartFile file,
			@RequestParam("userData") String userData
			){
		this.logger.info("File Information",file.getOriginalFilename());
		this.logger.info("user : {}",userData);

		//Converting string into json
		UserDto newUserDto = null;
		try{
			newUserDto = this.mapper.readValue(userData,UserDto.class);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		}
		this.logger.info("User Data : {}",newUserDto);
		this.logger.info("add user request");
		return ResponseEntity.ok(newUserDto);

	}

}
