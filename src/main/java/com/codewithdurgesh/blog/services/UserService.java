package com.codewithdurgesh.blog.services;

import java.util.List;

//import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto  user);
	UserDto updateUser(UserDto  user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllusers();
	void deleteUser(Integer userId);
	
	
	

}
