package com.MyDiary.servicelmpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyDiary.entity.User;
import com.MyDiary.repository.UserRepository;
import com.MyDiary.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	// Register new user
	@Override
	public boolean registerUser(User user) throws Exception {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new Exception("Username already exists!");
		}
		userRepository.save(user);
		return true;
	}

	// Validate login
	@Override
	public User validateLogin(String username, String password) {
		Optional<User> userOpt = userRepository.findByUsername(username);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	// Find user by username
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	// Get all users
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Delete user by ID
	@Override
	public boolean deleteUser(Long userId) {
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}
}
