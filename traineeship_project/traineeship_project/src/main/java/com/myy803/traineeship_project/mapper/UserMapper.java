package com.myy803.traineeship_project.mapper;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myy803.traineeship_project.domainmodel.User;

public interface UserMapper extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}