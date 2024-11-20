package com.dissertationproject.plant_stories.bean;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents a Users bean class used for data transfer or validation purposes.
 * This class does not map to a database but serves as a container for user-related data.
 */
public class Users {

    // Username field with validation to ensure it is not blank
    @NotBlank(message = "Username is required")
    private String username;

    // Password field with validation to ensure it is not blank and has a minimum length
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    // Confirm password field with validation to ensure it is not blank
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;


    // bio for user profile information
    private String bio;
    
    // Timestamp for when the user was created
    private Date createdAt;

    /**
     * Default constructor for creating a Users object.
     */
    public Users() {
    }

    /**
     * Overrides the toString method for debugging and logging purposes.
     * @return a string representation of the Users object
     */
    @Override
    public String toString() {
        return "Users [Username=" + username + ", password=" + password + 
               ", confirmPassword=" + confirmPassword + ", createdAt=" + createdAt + "]";
    }

    // Getters and setters for all fields

    /**
     * Gets the user's user name.
     * @return the user's user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's user name.
     * @param Username the user name to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's confirm password.
     * @return the confirm password field
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the user's confirm password.
     * @param confirmPassword the confirm password to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the timestamp when the user was created.
     * @return the creation timestamp
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the user was created.
     * @param createdAt the timestamp to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
    
    
    
}
