package com.usermanagement.userservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermanagement.dto.UserRequestDTO;
import com.usermanagement.dto.UserResponseDTO;
import com.usermanagement.models.User;
import com.usermanagement.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	
	
	//add user 
	public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
		
		User user=new User();
		
		user.setUserName(userRequestDTO.getUserName());
		user.setUserEmail(userRequestDTO.getUserEmail());
		user.setUserPassword(passwordEncoder.encode(userRequestDTO.getUserPassword()));
		user.setUserPhone(userRequestDTO.getUserPhone());
		user.setUserCreatedAt(LocalDateTime.now());
		user.setUserUpdatedAt(LocalDateTime.now());

		User savedUser=this.userRepo.save(user);
		
		return convertToResponseDTO(savedUser);
	}
	
	
	
	//get single user
	
	public UserResponseDTO geUserById(int userId) {
		
//			User user=userList.stream().filter(e->e.getUserId()==user_id).findFirst().get();

			User user=this.userRepo.findById(userId);

		
		return convertToResponseDTO(user);
		
		
	}
	
	
	
	//update user by id
	
	public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, int userId) {
	    User user = this.userRepo.findById(userId);
	    if (userRequestDTO.getUserPassword() != null && !userRequestDTO.getUserPassword().isEmpty()) {
	        user.setUserPassword(passwordEncoder.encode(userRequestDTO.getUserPassword()));
	    }
	    user.setUserName(userRequestDTO.getUserName());
	    user.setUserEmail(userRequestDTO.getUserEmail());
	    user.setUserPhone(userRequestDTO.getUserPhone());
	    user.setUserUpdatedAt(LocalDateTime.now());

	    User updatedUser = this.userRepo.save(user);

	    return convertToResponseDTO(updatedUser);
	}

	
	//getalluser  we need to give this code access to only admin role based cred
	
	public List<UserResponseDTO> getAllUsers(){
		
		
		List<User> usersList =this.userRepo.findAll();
		
		return usersList.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
		
	}
	
	
	//delete user
	
	public void deleteUser(int userId) {
		
		this.userRepo.deleteById(userId);
		
		
	}
	

	  
	private UserResponseDTO convertToResponseDTO(User user) {
		UserResponseDTO userResponseDTO=new UserResponseDTO();
		
		userResponseDTO.setUserId(user.getUserId());
		userResponseDTO.setUserName(user.getUserName());
		userResponseDTO.setUserEmail(user.getUserEmail());
		userResponseDTO.setUserPhone(user.getUserPhone());
		userResponseDTO.setUserCreatedAt(user.getUserCreatedAt());
		userResponseDTO.setUserUpdatedAt(user.getUserUpdatedAt());
		
		return userResponseDTO;
		
		
	}
	

}
