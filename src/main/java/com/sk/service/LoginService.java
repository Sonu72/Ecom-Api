package com.sk.service;

import com.sk.exception.LoginException;
import com.sk.model.Login;

public interface LoginService {

	public String loginAccount(Login loginDTO) throws LoginException;

	public String logoutAccount(String role, String key) throws LoginException;

}
