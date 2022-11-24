package com.app.service;

import com.app.exception.LoginException;
import com.app.model.LoginDTO;

public interface LoginService {

	String loginIntoAccount(LoginDTO dto) throws LoginException;
	
	String logoutFromAccount(String key) throws LoginException;
	
}
