package com.sk.servicelogic;

import org.springframework.stereotype.Service;

import com.sk.exception.LoginException;
import com.sk.model.Admin;
import com.sk.model.CurrentUserSession;
import com.sk.model.Customer;
import com.sk.model.Login;
import com.sk.repository.AdminRepo;
import com.sk.repository.CurrentUserSessionRepo;
import com.sk.repository.CustomerRepo;
import com.sk.service.LoginService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServicelogic implements LoginService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentUserSessionRepo sessionRepo;

	@Override
	public String loginAccount(Login loginDTO) throws LoginException {

		if (!loginDTO.getRole().equalsIgnoreCase("customer") && !loginDTO.getRole().equalsIgnoreCase("admin"))
			throw new LoginException("Please enter a valid role");

		if (loginDTO.getRole().equalsIgnoreCase("customer")) {
			Customer customer = customerRepo.findByEmail(loginDTO.getEmail());
			if (customer == null)
				throw new LoginException("Invalid email");

			if (customer.getPassword().equals(loginDTO.getPassword())) {

				CurrentUserSession cuurSession = sessionRepo.findByEmail(loginDTO.getEmail());

				if (cuurSession != null)
					throw new LoginException("User already logged-In!");

				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setEmail(loginDTO.getEmail());
				currentUserSession.setLoginDateTime(LocalDateTime.now());
				currentUserSession.setRole("customer");
				String privateKey = RandomString.make(6);
				currentUserSession.setPrivateKey(privateKey);

				sessionRepo.save(currentUserSession);
				return "Login Sucessufull!";
			} else {
				throw new LoginException("Please Enter a valid password");
			}

		} else if (loginDTO.getRole().equalsIgnoreCase("admin")) {
			Admin admin = adminRepo.findByEmail(loginDTO.getEmail());
			if (admin == null)
				throw new LoginException("Invalid email");

			if (admin.getPassword().equals(loginDTO.getPassword())) {

				CurrentUserSession cuurSession = sessionRepo.findByEmail(loginDTO.getEmail());

				if (cuurSession != null)
					throw new LoginException("User already logged-In!");

				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setEmail(loginDTO.getEmail());
				currentUserSession.setLoginDateTime(LocalDateTime.now());
				currentUserSession.setRole("admin");
				String privateKey = RandomString.make(6);
				currentUserSession.setPrivateKey(privateKey);

				sessionRepo.save(currentUserSession);
				return "Login Sucessufull!";
			} else {
				throw new LoginException("Please Enter a valid password");
			}
		}
		return null;
	}

	@Override
	public String logoutAccount(String role, String key) throws LoginException {

		if (!role.equalsIgnoreCase("customer") && !role.equalsIgnoreCase("admin"))
			throw new LoginException("Please enter a valid role");

		if (role.equalsIgnoreCase("customer")) {

			CurrentUserSession currSession = sessionRepo.findByPrivateKey(key);
			if (currSession == null)
				throw new LoginException("Invalid key");

			if (currSession.getRole().equalsIgnoreCase("customer")) {

				sessionRepo.delete(currSession);
				return "Logged Out!";

			} else
				throw new LoginException("Invalid role");

		} else if (role.equalsIgnoreCase("admin")) {

			CurrentUserSession currSession = sessionRepo.findByPrivateKey(key);
			if (currSession == null)
				throw new LoginException("Invalid key");

			if (currSession.getRole().equalsIgnoreCase("admin")) {

				sessionRepo.delete(currSession);
				return "Logged Out!";

			} else
				throw new LoginException("Invalid role");

		} else
			throw new LoginException("Invalid role");
	}
}
