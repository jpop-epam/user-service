package com.jpop.epam.us.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpop.epam.us.rest.model.db.LoginData;
import com.jpop.epam.us.rest.model.json.LoginJson;
import com.jpop.epam.us.rest.model.json.UserRegistrationJson;
import com.jpop.epam.us.rest.service.LoginService;
import com.jpop.epam.us.rest.service.UserRegistrationService;

@RestController
public class LoginRestController {
	private LoginService loginService;
	private UserRegistrationService userService;

	public LoginRestController(LoginService loginService, UserRegistrationService userService) {
		this.loginService = loginService;
		this.userService = userService;
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> login(@RequestBody LoginJson loginJson) {
		LoginData loginData = loginService.doLogin(loginJson);
		String userId = loginData.getUserId();

		UserRegistrationJson userDetails = userService.getUserById(userId);
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
}
