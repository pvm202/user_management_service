package com.usermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usermanagement.models.User;



public interface UserRepo extends  JpaRepository<User, Integer>{
	
	
	//find user
	public User  findById(int id);
	
	
	//find email by custom query
	
	@Query("select u from User u where u.userEmail=:userEmail")
	public User findUserByEmail(@Param("userEmail") String userEmail) ;
		
		
		
		
	
	
	
	
	
	
	

}
