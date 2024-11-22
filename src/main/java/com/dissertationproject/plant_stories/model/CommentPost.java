package com.dissertationproject.plant_stories.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the Comment entity for database storage.
 */
@Entity
@Table(name = "comment_post")
public class CommentPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the Comment table

    @Column(name = "comment_text", columnDefinition = "TEXT")
    private String commentText; // The text of the comment

    @Column(name = "reaction", columnDefinition = "TEXT")
    private String selectedReactions; // List of selected reactions

    @Column(name = "post_id", nullable = false)
    private Long postId; // Foreign key referencing the post

    @Column(name = "user_id", nullable = false)
    private Long userId; // Foreign key referencing the user who made the comment
    
    @Column(name = "username", nullable = false)
    private String username;
    

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now(); // Auto-populated timestamp
    
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getSelectedReactions() {
        return selectedReactions;
    }

    public void setSelectedReactions(String selectedReactions) {
        this.selectedReactions = selectedReactions;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", selectedReactions=" + selectedReactions +
                ", postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}

