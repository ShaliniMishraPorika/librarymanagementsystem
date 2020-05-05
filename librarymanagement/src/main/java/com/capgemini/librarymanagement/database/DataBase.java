package com.capgemini.librarymanagement.database;

import java.util.ArrayList;

import com.capgemini.librarymanagement.dto.AdminBean;
import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;

public class DataBase {
	public static final ArrayList<AdminBean> admin = new ArrayList<AdminBean>();
	public static final ArrayList<StudentBean> student = new ArrayList<StudentBean>();
	public static final ArrayList<BookBean> book = new ArrayList<BookBean>();
	public static final ArrayList<RequestBean> request = new ArrayList<RequestBean>();
	
	//AdminBean bean = new AdminBean(); 
}
