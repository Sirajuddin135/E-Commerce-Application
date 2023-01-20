package com.app.services;

import java.util.List;

import com.app.payloads.UserDTO;

public interface UserService {
	UserDTO registerUser(UserDTO userDTO);
	
	List<UserDTO> getAllUsers();
	
	UserDTO getUserById(Long userId);
	
	UserDTO updateUser(Long userId, UserDTO userDTO);
	
	String deleteUser(Long userId);
}
