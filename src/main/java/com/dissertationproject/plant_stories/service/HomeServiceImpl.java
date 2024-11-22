package com.dissertationproject.plant_stories.service;

import java.util.ArrayList;

import com.dissertationproject.plant_stories.bean.CommentForm;
import com.dissertationproject.plant_stories.bean.FeedPostMediaDTO;
import com.dissertationproject.plant_stories.bean.Posts;

import jakarta.validation.Valid;

public interface HomeServiceImpl {

	void createPost(Long userId, @Valid Posts post);

	ArrayList<FeedPostMediaDTO> getAllPosts();

	FeedPostMediaDTO getThisPostInfo(Long postId);

	void addComment(CommentForm commentForm, Long postId, Long id, String username);

	ArrayList<FeedPostMediaDTO> getAllPosts(Long userId);

	void deletePost(FeedPostMediaDTO feedPostMediaDTO);

	FeedPostMediaDTO getPost(Long postId);

	void deleteMedia(Long mediaId);


}