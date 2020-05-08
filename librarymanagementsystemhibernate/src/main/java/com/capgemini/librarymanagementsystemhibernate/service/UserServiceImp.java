package com.capgemini.librarymanagementsystemhibernate.service;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;
import com.capgemini.librarymanagementsystemhibernate.factory.UserFactory;

public class UserServiceImp implements UserService {
	UserDAO dao = UserFactory.getUsersDAO();
	public boolean register(UserBean info) {
		return dao.register(info);
	}

	public UserBean login(String email, String password) {
		return dao.login(email, password);
	}
}
