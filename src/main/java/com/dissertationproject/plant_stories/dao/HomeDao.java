package com.dissertationproject.plant_stories.dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.model.MediaPost;
import com.dissertationproject.plant_stories.model.Posts;

@Repository
public class HomeDao {
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private MediaPostsRepository mediaPostsRepository;
	

	public void createPost(Posts post, ArrayList<MediaPost> mediaPosts) {
		// TODO Auto-generated method stub
		Posts savedPost = postRepository.save(post);
		
		Long postId = savedPost.getId();
		
		for (MediaPost mediaPost : mediaPosts) {
			mediaPost.setPostId(postId);
			mediaPostsRepository.save(mediaPost);
		}
	}

}
