package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.database.DataBase;
import com.capgemini.librarymanagement.dto.UserBean;
import com.capgemini.librarymanagement.exception.LMSException;

public class UserDAOImp implements UserDAO{

	public boolean register(UserBean info) {
		for (UserBean adminBean : DataBase.user) {
			if (adminBean.getEmail().equals(info.getEmail())) {
				return false;
			}
		}
		DataBase.user.add(info);
		return true;

	}

	public UserBean login(String email, String password) {
		for (UserBean bean : DataBase.user) {
			if (bean.getEmail().equals(email) && bean.getPassword().equals(password)) {
				System.out.println("Login Successful");
				bean.getRole();
				return bean;
			}
		}
		throw new LMSException("Invalid email and password");

	}

}
