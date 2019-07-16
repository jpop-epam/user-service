package com.jpop.epam.batch2.team4.arnab.us.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpop.epam.batch2.team4.arnab.common.constants.ApplicationCommonConstants;
import com.jpop.epam.batch2.team4.arnab.common.constants.UserRoles;
import com.jpop.epam.batch2.team4.arnab.us.rest.exception.NoSuchUserException;
import com.jpop.epam.batch2.team4.arnab.us.rest.model.db.LoginData;
import com.jpop.epam.batch2.team4.arnab.us.rest.model.db.UserRegistrationData;
import com.jpop.epam.batch2.team4.arnab.us.rest.model.json.UserRegistrationJson;
import com.jpop.epam.batch2.team4.arnab.us.rest.repository.UserRegistrationRepository;
import com.jpop.epam.batch2.team4.arnab.us.rest.transformer.UserTransformer;

@Service
public class UserRegistrationService {
	private UserRegistrationRepository userRegistrationRepository;
	private LoginService loginService;

	public UserRegistrationService(UserRegistrationRepository userRegistrationRepository, LoginService loginService) {
		this.userRegistrationRepository = userRegistrationRepository;
		this.loginService = loginService;
	}

	public List<UserRegistrationJson> getAllUsers() {
		List<UserRegistrationJson> userRegistrationJsons = new ArrayList<>();
		
		userRegistrationRepository.findAll().forEach(user -> userRegistrationJsons.add(UserTransformer.userRegistrationToUserJson(user)));
		
		return userRegistrationJsons;
	}

	public UserRegistrationJson getUserById(String userId) {
		Optional<UserRegistrationData> searchResult = userRegistrationRepository.findById(userId);
		if (searchResult.isPresent()) {
			UserRegistrationData userRegistrationData = searchResult.get();
			return UserTransformer.userRegistrationToUserJson(userRegistrationData);
		}
		throw new NoSuchUserException();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserRegistrationJson addUser(UserRegistrationJson user) {
		user.setUserId("U" + System.currentTimeMillis());
		UserRegistrationData userRegData = UserTransformer.userJsonToUserRegistration(user);
		
		userRegData.setRegistrationDate(ApplicationCommonConstants.getCurrentDateAsString());
		userRegData.setUserAccessType(UserRoles.BASIC_USER.getAccessCode());

		LoginData loginData = UserTransformer.userJsonToUserLoginData(user);

		loginService.updateLoginInfo(loginData);
		
		userRegData.setLoginData(loginData);
		
		UserRegistrationData saved = userRegistrationRepository.save(userRegData);

		return UserTransformer.userRegistrationToUserJson(saved);
	}

	public void deleteUser(String userId) {

	}

	public UserRegistrationJson updateUser(String userId, UserRegistrationJson user) {
		return null;
	}
}
