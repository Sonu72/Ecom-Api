package com.sk.servicelogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.exception.SessionLoginException;
import com.sk.model.CurrentUserSession;
import com.sk.repository.CurrentUserSessionRepo;
import com.sk.service.SessionLoginService;

@Service
public class SessionLoginServicelogic implements SessionLoginService {

	@Autowired
	private CurrentUserSessionRepo sessionRepo;

	@Override
	public void checkAnyUserLoginStatus(String key) throws SessionLoginException {
		CurrentUserSession currSess = sessionRepo.findByPrivateKey(key);
		if (currSess == null)
			throw new SessionLoginException("User login required");
	}

	@Override
	public void checkAdminUserLoginStatus(String key) throws SessionLoginException {
		CurrentUserSession currSess = sessionRepo.findByPrivateKey(key);
		if (currSess != null && !currSess.getRole().equalsIgnoreCase("admin"))
			throw new SessionLoginException("Admin login required");
	}

	@Override
	public void checkCustomerUserLoginStatus(String key) throws SessionLoginException {
		CurrentUserSession currSess = sessionRepo.findByPrivateKey(key);
		if (currSess != null && !currSess.getRole().equalsIgnoreCase("customer"))
			throw new SessionLoginException("Admin login required");
	}

}
