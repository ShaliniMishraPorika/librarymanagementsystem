package com.capgemini.librarymanagementsystemspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspring.dao.UserDAO;
import com.capgemini.librarymanagementsystemspring.dto.UserBean;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDAO dao;

	public boolean register(UserBean info) {

		return dao.register(info);
	}

	public UserBean login(String email, String password) {

		return dao.login(email, password);
	}

}
