package com.capgemini.librarymanagement.service;

import com.capgemini.librarymanagement.dto.UserBean;

public interface UserService {
	boolean register(UserBean info);

	UserBean login(String email, String password);
}
