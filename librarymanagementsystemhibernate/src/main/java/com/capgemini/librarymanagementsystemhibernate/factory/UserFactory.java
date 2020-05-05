package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.UsersDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UsersDAOImp;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;
import com.capgemini.librarymanagementsystemhibernate.service.UserServiceImp;

public class UserFactory {
	public static UsersDAO getUsersDAO() {
		return new UsersDAOImp();
	}
	public static UserService  getUserService() {
		return new UserServiceImp();
	}
	
}
