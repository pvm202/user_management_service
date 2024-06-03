package com.usermanagement.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.models.User;
import com.usermanagement.repo.UserRepo;

@Service
public class UserValidationService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User findUserByEmail(String userEmail) {
		
		
		User user=this.userRepo.findUserByEmail(userEmail);
		
		return user;
	}

}
