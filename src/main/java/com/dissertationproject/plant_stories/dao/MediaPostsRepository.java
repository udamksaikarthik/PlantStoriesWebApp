package com.dissertationproject.plant_stories.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.model.MediaPost;

@Repository
public interface MediaPostsRepository extends JpaRepository<MediaPost, Integer>{

	ArrayList<MediaPost> findByPostId(Long id);

}
