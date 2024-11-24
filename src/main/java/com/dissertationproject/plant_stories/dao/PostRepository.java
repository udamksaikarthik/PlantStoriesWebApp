package com.dissertationproject.plant_stories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.model.Posts;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long>{
	
	Page<Posts> findAll(Pageable pageable);

	@Query("SELECT COUNT(p) FROM Posts p WHERE p.userId = :userId")
    long countById(@Param("userId") Long userId);
	
	// Custom query for pagination and filtering by userId
    @Query("SELECT p FROM Posts p WHERE p.userId = :userId")
    Page<Posts> findAllByUserId(@Param("userId") Long userId, Pageable pageable);
}
