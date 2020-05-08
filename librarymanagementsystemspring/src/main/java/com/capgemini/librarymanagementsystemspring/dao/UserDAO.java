package com.capgemini.librarymanagementsystemspring.dao;

import com.capgemini.librarymanagementsystemspring.dto.UserBean;

//@Service
public interface UserDAO {
	boolean register(UserBean info);

	UserBean login(String email, String password);

}
