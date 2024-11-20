package com.dissertationproject.plant_stories.bean;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a combined DTO for displaying post information with associated media.
 */
public class FeedPostMediaDTO {

    private Long postId; // ID of the post
    private String postTitle; // Title of the post
    private String postDescription; // Description of the post
    private String careRoutine; // Care routine
    private String reflection; // Reflections
    private String mood; // Mood
    private String weather; // Weather
    private String previousStoryAchievements; // Achievements
    private LocalDateTime createdDate; // Post creation date
    private Long userId; // ID of the user who created the post

    // List of media associated with the post
    private List<MediaDTO> mediaList;

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public List<MediaDTO> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<MediaDTO> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public String toString() {
        return "PostDetailsDTO{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", careRoutine='" + careRoutine + '\'' +
                ", reflection='" + reflection + '\'' +
                ", mood='" + mood + '\'' +
                ", weather='" + weather + '\'' +
                ", previousStoryAchievements='" + previousStoryAchievements + '\'' +
                ", createdDate=" + createdDate +
                ", userId=" + userId +
                ", mediaList=" + mediaList +
                '}';
    }

    /**
     * Represents media details associated with a post.
     */
    public static class MediaDTO {
        private Long id; // ID of the media
        private String mediaType; // Media type (e.g., image/jpeg, video/mp4)
        private String fileName; // File name
        private byte[] mediaData; // Binary data for the media

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public byte[] getMediaData() {
            return mediaData;
        }

        public void setMediaData(byte[] mediaData) {
            this.mediaData = mediaData;
        }

        @Override
        public String toString() {
            return "MediaDTO{" +
                    "id=" + id +
                    ", mediaType='" + mediaType + '\'' +
                    ", fileName='" + fileName + '\'' +
                    '}';
        }
    }
}

