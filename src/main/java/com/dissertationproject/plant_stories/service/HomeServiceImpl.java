package com.dissertationproject.plant_stories.service;

import java.util.ArrayList;

import com.dissertationproject.plant_stories.bean.FeedPostMediaDTO;
import com.dissertationproject.plant_stories.bean.Posts;

import jakarta.validation.Valid;

public interface HomeServiceImpl {

	void createPost(Long userId, @Valid Posts post);

	ArrayList<FeedPostMediaDTO> getAllPosts();

}
