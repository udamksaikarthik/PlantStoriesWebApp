package com.dissertationproject.plant_stories.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents the Users entity mapped to a database table.
 * This entity stores user-related data such as username, password, role, and creation timestamp.
 */
@Entity
public class Users {

    // Primary key with auto-incremented ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username field with validation and database constraints
    @NotBlank(message = "Username is required")
    @Column(name = "username", nullable = false)
    private String username;

    // Password field with validation and database constraints
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    @Column(name = "password", nullable = false)
    private String password;

    // Role field with a default value of "USER" and a non-null constraint
    @Column(name = "role", nullable = false)
    private String role = "USER";

    // Timestamp for when the user was created, not updatable after creation
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * Default constructor for JPA.
     */
    public Users() {
    }

    /**
     * Overrides the toString method for debugging and logging purposes.
     * @return a string representation of the Users object
     */
    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + username + ", password=" + password + 
               ", role=" + role + ", createdAt=" + createdAt + "]";
    }

    // Getters and setters for all fields

    /**
     * Gets the user ID.
     * @return the user's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username.
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the creation timestamp.
     * @return the user's creation timestamp
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     * @param createdAt the timestamp to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the role of the user.
     * @return the user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
