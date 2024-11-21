package com.dissertationproject.plant_stories.bean;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class EditProfileDTO {

    // Username field with validation to ensure it is not blank
    @NotBlank(message = "Username is required")
    private String username;
   
    // bio for user profile information
    private String bio;
    

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}


	@Override
	public String toString() {
		return "EditProfileDTO [username=" + username + ", bio=" + bio + "]";
	}
    
    
}
