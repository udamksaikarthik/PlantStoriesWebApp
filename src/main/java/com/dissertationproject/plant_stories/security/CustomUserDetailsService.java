package com.dissertationproject.plant_stories.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dissertationproject.plant_stories.dao.UserRepository;
import com.dissertationproject.plant_stories.model.Users;


/**
 * CustomUserDetailsService is a Spring Security service for retrieving user details.
 * It integrates with the application's user repository to fetch user data for authentication.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Injecting the UserRepository dependency to interact with the database
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user details by username (email in this application).
     *
     * @param username the email of the user attempting to authenticate
     * @return a UserDetails object containing user information for authentication
     * @throws UsernameNotFoundException if the user is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user from the database using the email (username)
        Users user = userRepository.findByEmail(email);
        
        // Throw an exception if the user is not found
        if (user == null) {
        	System.out.println("User not found with email: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Build and return a Spring Security UserDetails object
        return User
                .withUsername(user.getEmail()) // Set the email as the username
                .password(user.getPassword()) // Set the encoded password
                .roles(user.getRole())        // Assign roles to the user (e.g., "USER", "ADMIN")
                .build();
    }
}

