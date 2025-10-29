package com.MyDiary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MyDiary.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Find user by username
	Optional<User> findByUsername(String username);

	// Find user by email
	Optional<User> findByEmail(String email);

	// For login (simple check for demo — later use password encoder)
	Optional<User> findByUsernameAndPassword(String username, String password);
}
