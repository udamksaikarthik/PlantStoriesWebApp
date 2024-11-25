package com.dissertationproject.plant_stories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dissertationproject.plant_stories.bean.Users;
import com.dissertationproject.plant_stories.dao.UserDao;
import com.dissertationproject.plant_stories.model.PasswordResetToken;


@Service
public class UserService implements UserServiceImpl{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Boolean checkIfUsernameAlreadyExists(Users users) {
		// TODO Auto-generated method stub
		return userDao.checkIfUsernameAlreadyExists(users);
	}

	@Override
	public void registerUser(Users users) {
		// TODO Auto-generated method stub
		userDao.saveUser(users);
	}

	@Override
	public void editprofile(Long id, String bio) {
		// TODO Auto-generated method stub
		userDao.editprofile(id, bio);
	}

	@Override
	public String generateResetToken(String email) {
		// TODO Auto-generated method stub
		return userDao.generateResetToken(email);
	}

	@Override
	public PasswordResetToken findByToken(String token) {
		// TODO Auto-generated method stub
		return userDao.findByToken(token);
	}

	@Override
	public void delete(PasswordResetToken resetToken) {
		// TODO Auto-generated method stub
		userDao.delete(resetToken);
	}

}
