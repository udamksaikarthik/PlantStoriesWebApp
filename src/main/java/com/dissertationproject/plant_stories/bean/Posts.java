package com.dissertationproject.plant_stories.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * Represents a Posts bean class for data transfer or validation purposes.
 * This class includes the necessary properties for a post and ensures mandatory fields are validated.
 */
public class Posts {

    /**
     * Title of the post.
     * This field is mandatory and must not exceed 255 characters.
     */
    @NotBlank(message = "Post title is mandatory")
    @Size(max = 255, message = "Post title must not exceed 255 characters")
    private String postTitle;

    /**
     * Description of the post.
     * This field is mandatory.
     */
    @NotBlank(message = "Post description is mandatory")
    private String postDescription;

    /**
     * Optional details about the care routine for a plant.
     */
    private String careRoutine;

    /**
     * Optional reflections or thoughts about the post.
     */
    private String reflection;

    /**
     * Optional mood associated with the post.
     */
    private String mood;

    /**
     * Optional weather information associated with the post.
     */
    private String weather;
    

    /**
     * Optional previousStoryAchievements information associated with the current story post.
     */
    private String previousStoryAchievements;

    /**
     * List of media data (e.g., images or videos) associated with the post.
     * Each entry in the list represents a binary file in byte array format.
     */
    @Size(max = 5, message = "You can only upload up to 5 media files.")
    private List<MultipartFile> mediaData;

    /**
     * Timestamp of when the post was created.
     * Defaults to the current date and time.
     */
    private LocalDateTime createdDate = LocalDateTime.now();

    // Getters and Setters

    /**
     * Gets the title of the post.
     * @return the title of the post
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * Sets the title of the post.
     * @param postTitle the title to set
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    /**
     * Gets the description of the post.
     * @return the description of the post
     */
    public String getPostDescription() {
        return postDescription;
    }

    /**
     * Sets the description of the post.
     * @param postDescription the description to set
     */
    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    /**
     * Gets the care routine details of the post.
     * @return the care routine details
     */
    public String getCareRoutine() {
        return careRoutine;
    }

    /**
     * Sets the care routine details of the post.
     * @param careRoutine the care routine details to set
     */
    public void setCareRoutine(String careRoutine) {
        this.careRoutine = careRoutine;
    }

    /**
     * Gets the reflection or thoughts about the post.
     * @return the reflection details
     */
    public String getReflection() {
        return reflection;
    }

    /**
     * Sets the reflection or thoughts about the post.
     * @param reflection the reflection details to set
     */
    public void setReflection(String reflection) {
        this.reflection = reflection;
    }

    /**
     * Gets the mood associated with the post.
     * @return the mood of the post
     */
    public String getMood() {
        return mood;
    }

    /**
     * Sets the mood associated with the post.
     * @param mood the mood to set
     */
    public void setMood(String mood) {
        this.mood = mood;
    }

    /**
     * Gets the weather information associated with the post.
     * @return the weather details
     */
    public String getWeather() {
        return weather;
    }

    /**
     * Sets the weather information associated with the post.
     * @param weather the weather details to set
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /**
     * Gets the list of media data associated with the post.
     * @return the list of media data
     */
    public List<MultipartFile> getMediaData() {
        return mediaData;
    }

    /**
     * Sets the list of media data associated with the post.
     * @param mediaData the list of media data to set
     */
    public void setMediaData(List<MultipartFile> mediaData) {
        this.mediaData = mediaData;
    }

    /**
     * Gets the creation timestamp of the post.
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation timestamp of the post.
     * @param createdDate the creation timestamp to set
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    

    public String getPreviousStoryAchievements() {
		return previousStoryAchievements;
	}

	public void setPreviousStoryAchievements(String previousStoryAchievements) {
		this.previousStoryAchievements = previousStoryAchievements;
	}


	/**
     * Overrides the toString method for debugging and logging purposes.
     * @return a string representation of the Posts object
     */

	@Override
	public String toString() {
		return "Posts [postTitle=" + postTitle + ", postDescription=" + postDescription + ", careRoutine=" + careRoutine
				+ ", reflection=" + reflection + ", mood=" + mood + ", weather=" + weather
				+ ", previousStoryAchievements=" + previousStoryAchievements + ", mediaData=" + mediaData
				+ ", createdDate=" + createdDate + "]";
	}
}

