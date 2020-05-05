package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class UserDAOImp implements UserDAO {

	PreparedStatement prepareStatement = null;
	Connection connection = null;
	ResultSet rs;

	@Override
	public boolean register(UsersBean info) {
		connection = JdbcUtility.getConnection();
		try {
			prepareStatement = connection.prepareStatement(QueryMapper.register);
			prepareStatement.setInt(1, info.getId());
			prepareStatement.setString(2, info.getName());
			prepareStatement.setString(3, info.getMobile());
			prepareStatement.setString(4, info.getEmail());
			prepareStatement.setString(5, info.getPassword());
			prepareStatement.setString(6, info.getRole());
			int count = prepareStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Email already exists");
		}
		return false;
	}

	@Override
	public UsersBean login(String email, String password) {
		connection = JdbcUtility.getConnection();
//		try {
//			
//			prepareStatement = connection.prepareStatement(QueryMapper.login);
//			prepareStatement.setString(1, email);
//			prepareStatement.setString(2, password);
//			rs = prepareStatement.executeQuery();
//			if (rs.next()) {
//				UsersBean bean = new UsersBean();
//				bean.setEmail(rs.getString("email"));
//				bean.setPassword(rs.getString("password"));
//				bean.setId(rs.getInt("uId"));
//				bean.setMobile(rs.getString("mobile"));
//				bean.setRole(rs.getString("role"));
//				bean.setName(rs.getString("name"));
//				return bean;
//			}
//		} catch (Exception e) {
//			throw new LMSException("Invalid Credantials");
//		}

				try {
					prepareStatement = connection.prepareStatement(QueryMapper.email);
					prepareStatement.setString(1, email);
					rs = prepareStatement.executeQuery();
					while (rs.next()) {
						PreparedStatement prepareStatement1 = connection.prepareStatement(QueryMapper.login);
						prepareStatement1.setString(1, email);
						prepareStatement1.setString(2, password);
						ResultSet set = prepareStatement1.executeQuery();
						if (set.next()) {
							UsersBean bean = new UsersBean();
							bean.setEmail(rs.getString("email"));
							bean.setPassword(rs.getString("password"));
							bean.setId(rs.getInt("uId"));
							bean.setMobile(rs.getString("mobile"));
							bean.setRole(rs.getString("role"));
							bean.setName(rs.getString("name"));
							return bean;
						} else {
							System.out.println("Wrong Password. Try again");
						}
					}
				} catch (Exception e) {
					throw new LMSException("Invalid Credantials");
				}
		return null;
	}

}