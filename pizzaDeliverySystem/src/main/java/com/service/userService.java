package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.user;
import com.repository.userRepository;

@Service
public class userService implements UserDetailsService{
 
	@Autowired
	private userRepository userrepository;
	
	
	public user addUserData(user userdata) {	
		userdata.setPassword(passwordEncoder().encode(userdata.getPassword()));
		return this.userrepository.save(userdata);	
	}
	
	public user getUserData(String email) {
		return this.userrepository.findByEmail(email);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails data = this.userrepository.findByEmail(username);
		return data;
	}
	
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }

}
