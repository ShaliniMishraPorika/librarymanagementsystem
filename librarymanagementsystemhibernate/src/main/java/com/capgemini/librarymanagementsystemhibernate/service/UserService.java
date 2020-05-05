package com.capgemini.librarymanagementsystemhibernate.service;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;

public interface UserService {
	boolean register(UserBean info);
	UserBean login(String email, String password);	
}
