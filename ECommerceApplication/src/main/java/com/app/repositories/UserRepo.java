package com.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entites.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u JOIN FETCH u.addresses a WHERE a.addressId = ?1")
	List<User> findByAddress(Long addressId);
	
	Optional<User> findByEmail(String email);
}
