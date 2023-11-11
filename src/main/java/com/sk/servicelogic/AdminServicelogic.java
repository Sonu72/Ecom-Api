package com.sk.servicelogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.exception.AdminException;
import com.sk.model.Admin;
import com.sk.repository.AdminRepo;
import com.sk.service.AdminService;

@Service
public class AdminServicelogic implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public String createNewAdmin() throws AdminException {
		// TODO Auto-generated method stub

		String name = "Admin";
		String email = "admin@gmail.com";
		String password = "password";

		Admin existsAdmin = adminRepo.findByEmail(email);
		if (existsAdmin != null)
			throw new AdminException("Admin already created, the credentials => Email : " + email + ", Password : " + password);

		Admin admin = new Admin();
		admin.setName(name);
		admin.setEmail(email);
		admin.setPassword(password);

		adminRepo.save(admin);

		return "Admin created => Email : " + email + ", Password : " + password;
	}

}
