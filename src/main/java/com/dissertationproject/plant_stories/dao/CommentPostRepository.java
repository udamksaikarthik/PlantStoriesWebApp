package com.dissertationproject.plant_stories.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.model.CommentPost;

@Repository
public interface CommentPostRepository extends JpaRepository<CommentPost, Long>{

	ArrayList<CommentPost> findAllByPostId(Long postId);
	

	ArrayList<CommentPost> findAllByUserId(Long postId);


}
