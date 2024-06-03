package com.usermanagement.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {
	
		private Integer userId;
		
	    private String userName;
	    
	    private String userEmail;
	    
	    private String userPhone;
	    
	    private LocalDateTime userCreatedAt;
	    
	    private LocalDateTime userUpdatedAt;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getUserPhone() {
			return userPhone;
		}

		public void setUserPhone(String userPhone) {
			this.userPhone = userPhone;
		}

		public LocalDateTime getUserCreatedAt() {
			return userCreatedAt;
		}

		public void setUserCreatedAt(LocalDateTime userCreatedAt) {
			this.userCreatedAt = userCreatedAt;
		}

		public LocalDateTime getUserUpdatedAt() {
			return userUpdatedAt;
		}

		public void setUserUpdatedAt(LocalDateTime userUpdatedAt) {
			this.userUpdatedAt = userUpdatedAt;
		}
	    
	    

}
