package com.gcrm.devopsreports.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcrm.devopsreports.hobjects.User;
import com.gcrm.devopsreports.models.AuthRequest;
import com.gcrm.devopsreports.models.UserDto;
import com.gcrm.devopsreports.services.JwtServiceImpl;
import com.gcrm.devopsreports.services.UserServiceImpl;


@Controller
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private JwtServiceImpl jwtServiceimpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody UserDto userDto){
		return new ResponseEntity<User>(userService.saveUser(userDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllUsers")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getALLUsers() {
		return userService.getUsers();
	}
	

	@PostMapping("/authenticate")
	public String authenticateAndetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
//			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			return jwtServiceimpl.generateToken(authRequest.getUserName());
		} else
			throw new UsernameNotFoundException("Invalid User");
	}
		
//		return jwtServiceimpl.generateToken(authRequest.getUserName());}
}
