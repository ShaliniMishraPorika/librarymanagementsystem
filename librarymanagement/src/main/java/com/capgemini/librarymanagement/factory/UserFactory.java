package com.capgemini.librarymanagement.factory;

import com.capgemini.librarymanagement.dao.UserDAO;
import com.capgemini.librarymanagement.dao.UserDAOImp;
import com.capgemini.librarymanagement.service.UserServcieImp;
import com.capgemini.librarymanagement.service.UserService;

public class UserFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOImp();
	}
	public static UserService  getUserService() {
		return (UserService) new UserServcieImp();
	}
}
