package com.dissertationproject.plant_stories.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.model.Users;

/**
 * UserRepository is a data access layer interface for performing CRUD operations
 * on the `Users` entity. It extends JpaRepository to leverage Spring Data JPA functionalities.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * Custom method to find a user by their username.
     * Spring Data JPA automatically implements this method based on the method name.
     *
     * @param username the username of the user to retrieve
     * @return the Users object if found, otherwise null
     */
    Users findByUsername(String username);

	Users findByEmail(String email);
}

