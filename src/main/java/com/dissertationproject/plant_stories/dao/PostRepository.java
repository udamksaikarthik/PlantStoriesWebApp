package com.dissertationproject.plant_stories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.model.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long>{

}
