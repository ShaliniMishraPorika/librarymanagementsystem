package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;

public interface UserService {
	boolean register(UsersBean info);
	UsersBean login(String email, String password);
}
