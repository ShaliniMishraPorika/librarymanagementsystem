package com.capgemini.librarymanagementsystemspring.service;

import com.capgemini.librarymanagementsystemspring.dto.UserBean;

public interface UserService {
	boolean register(UserBean info);

	UserBean login(String email, String password);
}
