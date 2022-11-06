package com.joueurs.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joueurs.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByName(String name);
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByUsernameOrEmail(String name,String email);
	
	Boolean existsByUsername (String username);
	
	Boolean existsByEmail (String email);
	
	
	
}
