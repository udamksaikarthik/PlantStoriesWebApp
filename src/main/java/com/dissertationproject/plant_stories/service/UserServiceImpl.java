package com.dissertationproject.plant_stories.service;

import com.dissertationproject.plant_stories.bean.Users;
import com.dissertationproject.plant_stories.model.PasswordResetToken;

import jakarta.validation.Valid;

public interface UserServiceImpl {

	Boolean checkIfEmailAlreadyExists(@Valid Users users);

	void registerUser(@Valid Users users);

	void editprofile(Long id, String bio, String string);

	String generateResetToken(String email);

	PasswordResetToken findByToken(String token);

	void delete(PasswordResetToken resetToken);

}
