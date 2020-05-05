package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;

public class UsersServiceImp  implements UserService{
	UserDAO dao = LMSFactory.getUserDAO();
	@Override
	public boolean register(UsersBean info) {
		
		return dao.register(info);
	}
	@Override
	public UsersBean login(String email, String password) {
		
		return dao.login(email, password);
	}
}
