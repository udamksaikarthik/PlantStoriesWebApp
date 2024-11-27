package com.dissertationproject.plant_stories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.model.LikeCounter;

@Repository
public interface LikeCounterRepository extends JpaRepository<LikeCounter, Long>{

	LikeCounter findByWebAppName(String webAppName);


}
