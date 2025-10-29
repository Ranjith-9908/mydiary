package com.MyDiary.service;

import java.util.List;

import com.MyDiary.entity.User;

public interface UserService {

	// Register a new user
	boolean registerUser(User user) throws Exception;

	// Login validation (username + password)
	User validateLogin(String username, String password);

	// Find a user by username
	User getUserByUsername(String username);

	// Fetch all users
	List<User> getAllUsers();

	// Delete user by ID
	boolean deleteUser(Long userId);
}
