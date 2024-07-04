package com.gcrm.devopsreports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcrm.devopsreports.hobjects.User;
import com.gcrm.devopsreports.models.UserDto;
import com.gcrm.devopsreports.repositories.UserRepository;



@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		return userRepository.save(user);
	}

	
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	
	


}
