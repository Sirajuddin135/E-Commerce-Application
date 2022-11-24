package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CurrentUserSession;

public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {
	
	CurrentUserSession findByUuid(String uuid);

}
