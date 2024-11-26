package com.dissertationproject.plant_stories.service;

import com.dissertationproject.plant_stories.bean.Users;
import com.dissertationproject.plant_stories.model.PasswordResetToken;

import jakarta.validation.Valid;

public interface UserServiceImpl {

	Boolean checkIfUsernameAlreadyExists(@Valid Users users);

	void registerUser(@Valid Users users);

	void editprofile(Long id, String bio);

	String generateResetToken(String email);

	PasswordResetToken findByToken(String token);

	void delete(PasswordResetToken resetToken);

}
