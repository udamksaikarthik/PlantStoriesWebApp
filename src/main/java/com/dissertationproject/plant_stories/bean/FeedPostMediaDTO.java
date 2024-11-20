package com.dissertationproject.plant_stories.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a combined DTO for displaying post information with associated media and comments.
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
    private String username; // Username of the user who created the post

    // List of media associated with the post
    private List<MediaDTO> mediaList;

    // List of comments associated with the post
    private List<CommentPostDTO> commentList;

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<CommentPostDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentPostDTO> commentList) {
        this.commentList = commentList;
    }

    // Getter for the formatted date
    public String getFormattedCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a");
        return createdDate != null ? createdDate.format(formatter) : null;
    }

    @Override
    public String toString() {
        return "FeedPostMediaDTO{" +
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
                ", username='" + username + '\'' +
                ", mediaList=" + mediaList +
                ", commentList=" + commentList +
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
        private String base64MediaData; // Base64-encoded data for rendering

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

        public String getBase64MediaData() {
            return base64MediaData;
        }

        public void setBase64MediaData(String base64MediaData) {
            this.base64MediaData = base64MediaData;
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

    /**
     * Represents comment details associated with a post.
     */
    public static class CommentPostDTO {
        private Long id; // Comment ID
        private String commentText; // The text of the comment
        private String selectedReactions; // Reactions associated with the comment
        private Long userId; // ID of the user who made the comment
        private String username; // Username of the commenter
        private LocalDateTime createdDate; // Date the comment was created

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
            return "CommentPostDTO{" +
                    "id=" + id +
                    ", commentText='" + commentText + '\'' +
                    ", selectedReactions='" + selectedReactions + '\'' +
                    ", userId=" + userId +
                    ", username='" + username + '\'' +
                    ", createdDate=" + createdDate +
                    '}';
        }
    }
}
