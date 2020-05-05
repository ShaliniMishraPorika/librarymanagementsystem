package com.capgemini.librarymanagementsystemhibernate.dao;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;

public interface UsersDAO {
	boolean register(UserBean info);

	UserBean login(String email, String password);

}
