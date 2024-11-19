package com.dissertationproject.plant_stories.service;

import com.dissertationproject.plant_stories.bean.Users;

import jakarta.validation.Valid;

public interface UserServiceImpl {

	Boolean checkIfUsernameAlreadyExists(@Valid Users users);

	void registerUser(@Valid Users users);

}
