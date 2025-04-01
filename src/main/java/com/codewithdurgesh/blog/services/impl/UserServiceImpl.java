package com.codewithdurgesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.codewithdurgesh.blog.exceptions.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		
		return this.userToDto(savedUser) ;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1 =this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto UserDto) {
		User user=this.modelMapper.map(UserDto, User.class); 
		
		
		
//		user.setId(UserDto.getId());
//		user.setName(UserDto.getName());
//		user.setEmail(UserDto.getEmail());
//		user.setAbout(UserDto.getAbout());
//		user.setPassword(UserDto.getPassword());
		
		return user;
	}
	public UserDto userToDto(User user) {
	    UserDto userDto = this.modelMapper.map(user, UserDto.class);
	 
	    
//	    userDto.setId(user.getId());
//	    userDto.setName(user.getName());
//	    userDto.setEmail(user.getEmail());
//	    userDto.setAbout(user.getAbout());
//	    userDto.setPassword(user.getPassword());

	    return userDto;
	}


}
