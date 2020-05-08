package com.capgemini.librarymanagementsystemhibernate.dao;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;

public interface UserDAO {
	boolean register(UserBean info);

	UserBean login(String email, String password);

}
