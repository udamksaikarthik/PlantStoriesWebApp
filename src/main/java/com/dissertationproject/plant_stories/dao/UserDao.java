package com.dissertationproject.plant_stories.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.bean.Users;

@Repository
public class UserDao {


	@Autowired
	private UserRepository userRepository;
	

    @Autowired
    private PasswordEncoder passwordEncoder; 
    
	public Boolean checkIfUsernameAlreadyExists(Users users) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		String user_name = users.getUsername().trim();
		com.dissertationproject.plant_stories.model.Users user = userRepository.findByUsername(user_name);
		if(user!=null) {
			System.out.println("Username already exists");
			flag = true;
		}else {
			System.out.println("New Username");
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

        return userEntity;
	}

}
