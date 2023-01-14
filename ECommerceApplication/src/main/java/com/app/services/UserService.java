package com.app.services;

import java.util.List;

import com.app.payloads.UserDTO;

public interface UserService {
	UserDTO registerUser(UserDTO userDTO);
	
	List<UserDTO> getAllUsers();
	
	UserDTO getUserById(Integer userId);
	
	UserDTO updateUser(Integer userId, UserDTO userDTO);
	
	String deleteUser(Integer userId);
}
