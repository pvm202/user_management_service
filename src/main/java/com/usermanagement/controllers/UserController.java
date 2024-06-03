package com.usermanagement.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.dto.UserRequestDTO;
import com.usermanagement.dto.UserResponseDTO;
import com.usermanagement.models.AuthRequest;
import com.usermanagement.models.User;
import com.usermanagement.repo.UserRepo;
import com.usermanagement.userservice.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo repo;

	
	
	
	// get single user by id
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserResponseDTO> getUser(@PathVariable("userId") int userId) {
		logger.info("Fetching user with id: {}", userId);

		UserResponseDTO user = this.userService.geUserById(userId);
		if (user == null) {
			logger.warn("User with id {} not found", userId);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		logger.info("User found: {}", userId);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	
	
	
	// get all users(only for administrator role)
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

		List<UserResponseDTO> allUsers = this.userService.getAllUsers();

		if (allUsers.isEmpty()) {
			logger.warn("user bucket is empty");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

		logger.info("user list extracted..");
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);

	}

	// add new user
	@PostMapping(value = "/addUser", consumes = "application/json", produces = "application/json")

	public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO addedUser = null;

		try {
			addedUser = this.userService.addUser(userRequestDTO);
			logger.info("user created: {} ", addedUser.getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);

		} catch (Exception e) {
			logger.error("error creating user", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	//update user by id
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@Valid  @RequestBody UserRequestDTO updatedUser, @PathVariable("userId") int userId) {
		try {
			UserResponseDTO existingUser = this.userService.geUserById(userId);
			if (existingUser != null) {

				this.userService.updateUser(updatedUser, userId);
				logger.info("user updated successfully:{}", userId);
				return ResponseEntity.status(HttpStatus.CREATED).body(existingUser);

			} else {
				logger.warn("user not found with this id:{}", userId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (Exception e) {
			logger.error("error with updating user", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	//delete user
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {

		try {
			this.userService.deleteUser(userId);
			logger.info("user deleted: {}", userId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} catch (Exception e) {
			logger.warn("user not found", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	
	
	

	
	
	
	

}
