package com.dissertationproject.plant_stories.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.bean.Users;
import com.dissertationproject.plant_stories.model.PasswordResetToken;

@Repository
public class UserDao {


	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	

    @Autowired
    private PasswordEncoder passwordEncoder; 
    
	public Boolean checkIfEmailAlreadyExists(Users users) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		String email = users.getEmail().trim();
		com.dissertationproject.plant_stories.model.Users user = userRepository.findByEmail(email);
		if(user!=null) {
			System.out.println("email already exists");
			flag = true;
		}else {
			System.out.println("New email");
			flag = false;
		}
		return flag;
	}

	public void saveUser(Users users) {
		// TODO Auto-generated method stub
		com.dissertationproject.plant_stories.model.Users userEntity = new com.dissertationproject.plant_stories.model.Users();
		userEntity = convertToUserEntity(users);
		userRepository.save(userEntity);
	}

	private com.dissertationproject.plant_stories.model.Users convertToUserEntity(Users users) {
		com.dissertationproject.plant_stories.model.Users userEntity = new com.dissertationproject.plant_stories.model.Users();

        userEntity.setUsername(users.getUsername());

        // Encrypt the password before saving
        userEntity.setPassword(passwordEncoder.encode(users.getPassword()));

        userEntity.setCreatedAt(new Date());
        

        userEntity.setEmail(users.getEmail());

        return userEntity;
	}

	public void editprofile(Long id, String bio) {
		// TODO Auto-generated method stub
		Optional<com.dissertationproject.plant_stories.model.Users> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			com.dissertationproject.plant_stories.model.Users existinguser = user.get();
			existinguser.setBio(bio);
			userRepository.save(existinguser);
		}
	}
	
	public String generateResetToken(String email) {
        Optional<com.dissertationproject.plant_stories.model.Users> userOptional = Optional.of(userRepository.findByEmail(email));
        if (userOptional.isPresent()) {
            com.dissertationproject.plant_stories.model.Users user = userOptional.get();
            String token = UUID.randomUUID().toString();

            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // 1-hour expiration

            passwordResetTokenRepository.save(resetToken);
            return token;
        } else {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }
    }

	public PasswordResetToken findByToken(String token) {
		// TODO Auto-generated method stub
		
		System.out.println("Inside PasswordResetToken");
		
		return passwordResetTokenRepository.findByToken(token);
	}

	public void delete(PasswordResetToken resetToken) {
		// TODO Auto-generated method stub
		passwordResetTokenRepository.delete(resetToken);
	}

}
