package com.capgemini.librarymanagementsystemjdbc.dao;

import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;

public interface UserDAO {
	boolean register(UsersBean info);
	UsersBean login(String email, String password);
}
