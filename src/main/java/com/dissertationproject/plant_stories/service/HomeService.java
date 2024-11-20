package com.dissertationproject.plant_stories.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dissertationproject.plant_stories.bean.CommentForm;
import com.dissertationproject.plant_stories.bean.FeedPostMediaDTO;
import com.dissertationproject.plant_stories.bean.Posts;
import com.dissertationproject.plant_stories.dao.HomeDao;
import com.dissertationproject.plant_stories.model.CommentPost;
import com.dissertationproject.plant_stories.model.MediaPost;

import jakarta.validation.Valid;

@Service
public class HomeService implements HomeServiceImpl{

	@Autowired
	private HomeDao homeDao;
	
	@Override
	public void createPost(Long userId, @Valid Posts post) {
		// TODO Auto-generated method stub
		System.out.println("createPost method from service layer");
    	com.dissertationproject.plant_stories.model.Posts posts = convertPostBeantoPostEntity(userId, post);
    	ArrayList<MediaPost> mediaPosts = getAllEntriesOfMedia(userId, post);
		homeDao.createPost(posts, mediaPosts);
	}


	@Override
	public ArrayList<FeedPostMediaDTO> getAllPosts() {
		// TODO Auto-generated method stub
		return homeDao.getAllPosts();
	}
	
	private ArrayList<MediaPost> getAllEntriesOfMedia(Long userId, @Valid Posts post) {
		// TODO Auto-generated method stub
		ArrayList<MediaPost> mediaPosts = new ArrayList<>();
		if(post!=null) {
			// Handle media files if provided
	        List<MultipartFile> mediaFiles = post.getMediaData();
	        if (mediaFiles != null && !mediaFiles.isEmpty()) {
	        	System.out.println("mediaFiles Size: "+mediaFiles.size());
	            for (MultipartFile file : mediaFiles) {
	    			MediaPost mediaPost = new MediaPost();
	                // Process the media file (e.g., save to database or file system)
	                byte[] fileData = null;
					try {
						fileData = file.getBytes();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                String fileName = file.getOriginalFilename();
	                String contentType = file.getContentType();
	                
	                mediaPost.setMediaData(fileData);
	                mediaPost.setFileName(fileName);
	                mediaPost.setMediaType(contentType);
	                mediaPost.setUserId(userId);
	                mediaPosts.add(mediaPost);
	            }
	        }
		}
		if(mediaPosts!=null) {
			System.out.println("mediaPosts: "+mediaPosts.size());
		}
		return mediaPosts;
	}

	private com.dissertationproject.plant_stories.model.Posts convertPostBeantoPostEntity(Long userId, @Valid Posts post) {

		com.dissertationproject.plant_stories.model.Posts postEntity = new com.dissertationproject.plant_stories.model.Posts();
		if(post!=null) {
	                
		        	postEntity.setPostTitle(post.getPostTitle());
		        	postEntity.setPostDescription(post.getPostDescription());
		        	postEntity.setCareRoutine(post.getCareRoutine());
		        	postEntity.setCreatedDate(post.getCreatedDate());
		        	postEntity.setMood(post.getMood());
		        	postEntity.setWeather(post.getWeather());
		        	postEntity.setPreviousStoryAchievements(post.getPreviousStoryAchievements());;
		        	postEntity.setUserId(userId);
		        	postEntity.setReflection(post.getReflection());
		        	
	            }
		return postEntity;
		
	}


	@Override
	public FeedPostMediaDTO getThisPostInfo(Long postId) {
		// TODO Auto-generated method stub
		return homeDao.getThisPostInfo(postId);
	}


	@Override
	public void addComment(CommentForm commentForm, Long postId, Long id, String username) {
		// TODO Auto-generated method stub
		CommentPost commentPost = new CommentPost();
		commentPost.setCommentText(commentForm.getCommentText());
		String selectedReactions = "";

		// Iterate over selected reactions and concatenate with a comma
		for (String selectedReaction : commentForm.getSelectedReactions()) {
		    if (!selectedReactions.isEmpty()) {
		        selectedReactions += ", "; // Add a comma only if the string is not empty
		    }
		    selectedReactions += selectedReaction;
		}
		commentPost.setSelectedReactions(selectedReactions);
		commentPost.setPostId(postId);
		commentPost.setUserId(id);
		commentPost.setUsername(username);
		homeDao.addComment(commentPost);
	}




}
