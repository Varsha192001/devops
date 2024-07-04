package com.gcrm.devopsreports.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gcrm.devopsreports.hobjects.User;
import com.gcrm.devopsreports.repositories.UserRepository;



@Component
public class UserInfoDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userEntity = userRepo.findByUserName(username);

		return userEntity.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found" + username));	
	}}