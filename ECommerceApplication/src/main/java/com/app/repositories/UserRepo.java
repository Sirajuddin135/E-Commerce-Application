package com.app.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@EntityGraph(attributePaths = {"address"})
	User findByEmail(String email);
}
