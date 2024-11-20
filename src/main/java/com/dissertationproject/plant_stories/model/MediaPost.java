package com.dissertationproject.plant_stories.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "mediaposts")
public class MediaPost {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the Posts table
    
	@Lob
    @Column(name = "media_data", nullable = true, columnDefinition = "LONGBLOB")
    private byte[] mediaData; // Binary data for the media file

    @Column(name = "media_type")
    private String mediaType; // Type of media (e.g., "image/jpeg", "video/mp4")

    @Column(name = "file_name")
    private String fileName; // Optional file name for the media
    
    @Column(name = "user_id", nullable = false)
    private Long userId; // Foreign key referencing the Users table
    
    @Column(name = "post_id", nullable = false)
    private Long postId; // Foreign key referencing the Posts table

	@Override
	public String toString() {
		return "MediaPost [id=" + id + ", mediaData=" + Arrays.toString(mediaData) + ", mediaType=" + mediaType
				+ ", fileName=" + fileName + ", userId=" + userId + ", postId=" + postId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getMediaData() {
		return mediaData;
	}

	public void setMediaData(byte[] mediaData) {
		this.mediaData = mediaData;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
    
    
}
