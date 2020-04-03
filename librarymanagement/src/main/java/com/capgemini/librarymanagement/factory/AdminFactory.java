package com.capgemini.librarymanagement.factory;

import com.capgemini.librarymanagement.dao.AdminDAO;
import com.capgemini.librarymanagement.dao.AdminDAOImp;
import com.capgemini.librarymanagement.service.AdminService;
import com.capgemini.librarymanagement.service.AdminServiceImp;

public class AdminFactory {
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImp();
	}
	public static AdminService  getAdminService() {
		return new AdminServiceImp();
	}
}
