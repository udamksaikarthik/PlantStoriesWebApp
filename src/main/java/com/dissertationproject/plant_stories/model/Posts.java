package com.dissertationproject.plant_stories.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents the Posts entity with each media file stored as a separate row.
 */
@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the Posts table

    @Column(name = "post_title", nullable = false, length = 255)
    private String postTitle; // Title of the post (repeated in each row)

    @Column(name = "post_description", nullable = false, columnDefinition = "TEXT")
    private String postDescription; // Description of the post (repeated in each row)

    @Column(name = "care_routine", columnDefinition = "TEXT")
    private String careRoutine; // Care routine (optional)

    @Column(name = "reflection", columnDefinition = "TEXT")
    private String reflection; // Reflection (optional)

    @Column(name = "mood")
    private String mood; // Mood (optional)

    @Column(name = "weather")
    private String weather; // Weather (optional)

    @Column(name = "previous_story_achievements", columnDefinition = "TEXT")
    private String previousStoryAchievements; // Achievements (optional)

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now(); // Auto-populated timestamp

    @Column(name = "user_id", nullable = false)
    private Long userId; // Foreign key referencing the Users table

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getCareRoutine() {
        return careRoutine;
    }

    public void setCareRoutine(String careRoutine) {
        this.careRoutine = careRoutine;
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPreviousStoryAchievements() {
        return previousStoryAchievements;
    }

    public void setPreviousStoryAchievements(String previousStoryAchievements) {
        this.previousStoryAchievements = previousStoryAchievements;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", careRoutine='" + careRoutine + '\'' +
                ", reflection='" + reflection + '\'' +
                ", mood='" + mood + '\'' +
                ", weather='" + weather + '\'' +
                ", previousStoryAchievements='" + previousStoryAchievements + '\'' +
                ", createdDate=" + createdDate +
                ", userId=" + userId +
                '}';
    }
}

