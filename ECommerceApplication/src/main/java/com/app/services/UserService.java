package com.app.services;

import com.app.payloads.UserDTO;

public interface UserService {
	UserDTO registerUser(UserDTO userDTO);
	
	UserDTO getUserById(Integer userId);
	
	UserDTO updateUser(Integer userId, UserDTO userDTO);
	
	String deleteUser(Integer userId);
}
