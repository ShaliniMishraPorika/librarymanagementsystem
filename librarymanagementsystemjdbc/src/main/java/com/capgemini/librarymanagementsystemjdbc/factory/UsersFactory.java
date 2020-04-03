package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAOImp;
import com.capgemini.librarymanagementsystemjdbc.service.UsersService;
import com.capgemini.librarymanagementsystemjdbc.service.UsersServiceImp;

public class UsersFactory {
	public static UsersDAO getUsersDAO() {
		return new UsersDAOImp();
	}
	public static UsersService  getAdminService() {
		return new  UsersServiceImp();
	}
}
