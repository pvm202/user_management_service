package com.usermanagement.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.usermanagement.models.AuthRequest;
import com.usermanagement.models.User;
import com.usermanagement.userservice.UserValidationService;

@RestController
@RequestMapping("/auth")
public class UserValidationContoller {

	@Autowired
	private UserValidationService userValidationService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(UserValidationContoller.class);

	// request will come here handle it properly and send response to receiving service


	@PostMapping(value = "/validate")
	public ResponseEntity<Boolean> validateUser(@RequestBody AuthRequest authRequest) {
		
		logger.info("fetching user from database");
		try {
			
			// validation logic
			User user = userValidationService.findUserByEmail(authRequest.getUserName());
			
			logger.info("user fetched from database:{}",user.getUserId());
			
			if (user != null && passwordEncoder.matches(authRequest.getUserPassword(), user.getUserPassword())) {
				logger.info("user validated successfully:{}",user.getUserId());
				
				return ResponseEntity.status(HttpStatus.OK).body(true);
				
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		
		} 
		catch (Exception e) {
			logger.error("user could not be validated..");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

}
