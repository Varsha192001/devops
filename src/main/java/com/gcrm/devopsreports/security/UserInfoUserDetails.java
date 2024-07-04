package com.gcrm.devopsreports.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gcrm.devopsreports.hobjects.User;



public class UserInfoUserDetails implements UserDetails{
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserInfoUserDetails(User userEntity) {
		userName=userEntity.getUserName();
		password=userEntity.getPassword();
//		authorities=Arrays.stream(userEntity.getRoles().split(","))
//				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());		
		authorities=null;}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
