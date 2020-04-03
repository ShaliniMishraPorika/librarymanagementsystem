package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAOImp;
import com.capgemini.librarymanagementsystemjdbc.service.UsersService;
import com.capgemini.librarymanagementsystemjdbc.service.UsersServiceImp;

public class LibraryFactory {
	public static UsersDAO getUsersDao() {
		return new UsersDAOImp();
	}
	public static UsersService getUsersService() {
		return new UsersServiceImp();
	}
}
