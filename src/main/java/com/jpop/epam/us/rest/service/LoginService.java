package com.jpop.epam.us.rest.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jpop.epam.common.crypto.CryptographyUtil;
import com.jpop.epam.common.service.exception.InvalidCredentialsException;
import com.jpop.epam.us.rest.model.db.LoginData;
import com.jpop.epam.us.rest.model.json.LoginJson;
import com.jpop.epam.us.rest.repository.LoginRepository;

@Service
public class LoginService {
	private LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public LoginData doLogin(LoginJson loginJson) {
		Optional<LoginData> searchResult = loginRepository.findById(loginJson.getUserName());

		if (searchResult.isPresent()) {
			LoginData saved = searchResult.get();

			String encryptedPassword = CryptographyUtil.encrypt(loginJson.getPassword(), saved.getSecret());

			if (saved.getPassword().equals(encryptedPassword)) {
				saved.setPassword(null);
				saved.setSecret(null);
				return saved;
			}
		}
		throw new InvalidCredentialsException();
	}

	public void updateLoginInfo(LoginData loginData) {
		String secret = CryptographyUtil.generateSecret();

		String encryptedPassword = CryptographyUtil.encrypt(loginData.getPassword(), secret);

		loginData.setSecret(secret);
		loginData.setPassword(encryptedPassword);

//		toSave = loginRepository.save(toSave);
//
//		toSave.setPassword(null);
//		toSave.setSecret(null);
	}
}
