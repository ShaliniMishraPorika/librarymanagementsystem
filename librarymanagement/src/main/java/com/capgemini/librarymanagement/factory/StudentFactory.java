package com.capgemini.librarymanagement.factory;

import com.capgemini.librarymanagement.dao.StudentDAO;
import com.capgemini.librarymanagement.dao.StudentDAOImp;
import com.capgemini.librarymanagement.service.StudentService;
import com.capgemini.librarymanagement.service.StudentServiceImp;


public class StudentFactory {
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImp();
	}
	public static StudentService getStudentService() {
		return new StudentServiceImp();
	}
}
