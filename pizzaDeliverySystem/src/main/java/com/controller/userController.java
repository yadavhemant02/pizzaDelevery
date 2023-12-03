package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.helper.Helper;
import com.model.JwtResponse;
import com.model.user;
import com.service.userService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class userController {

	@Autowired
	private userService userservice;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private Helper jwtService;

	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody user userdata) {

		if (userdata.getPassword().length() >= 8) {
			try {
				user newUser = this.userservice.addUserData(userdata);
				return new ResponseEntity<user>(newUser, HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<String>("bad Request" + e, HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>("password length will be more then 8", HttpStatus.BAD_REQUEST);
		}
	}

//	@GetMapping("/nn")
//	public String getUser(HttpServletRequest request) {
//		System.out.print(this.userservice.getUserData("yadav@gmail.com"));
//		String requestHeader = request.getHeader("Authorization");
//		System.out.print(requestHeader);
//		return "kk";
//	}

	@PostMapping("/login")
	public ResponseEntity<?> userlogin(@RequestBody user data) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
		if (authentication.isAuthenticated()) {
			String token = this.jwtService.generateToken(data);
			return new ResponseEntity<JwtResponse>(new JwtResponse(token, data.getEmail()), HttpStatus.OK);
		} else {
			// throw new UsernameNotFoundException("invalid user request !");
			return new ResponseEntity<String>("jjjj", HttpStatus.OK);
		}

	}

}
