package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.dto.UserBean;

public interface UserDAO {
	boolean register(UserBean info);

	UserBean login(String email, String password);
}
