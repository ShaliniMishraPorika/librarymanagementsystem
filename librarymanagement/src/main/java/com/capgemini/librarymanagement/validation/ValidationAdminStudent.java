package com.capgemini.librarymanagement.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagement.exception.LMSException;

public class ValidationAdminStudent {
	public boolean validatedId(int id) throws LMSException {
		String idRegEx = "[1-9]{1}[0-9]{5}";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LMSException("Id should contains exactly 6 digits");
		}
		return result;
	}

	public boolean validatedName(String name) throws LMSException {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Name should  contains only Alphabates");
		}
		return result;
	}

	public boolean validatedMobile(String mobile) throws LMSException {
		String mobileRegEx = "(0/91)?[6-9][0-9]{9}";
		boolean result = false;
		Pattern pattern = Pattern.compile(mobileRegEx);
		Matcher matcher = pattern.matcher(mobile);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Mobile Number  will start with  6 or 9 and It should contains 10 numbers ");
		}
		return result;
	}

	public boolean validatedEmail(String email) throws LMSException {
		String emailRegEx = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Enter proper email ");
		}
		return result;
	}

	public boolean validatedPassword(String password) throws LMSException {
		String passwordRegEx = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) {
			result = true;
		} else {
			throw new LMSException(
					"Password should contain atleast 8 characters ,one uppercase,one lowercase,one symbol ");
		}

		return result;
	}

	public boolean validatedDate(String date) throws LMSException {
		String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) date);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Enter proper Date Format");
		}
		return result;
	}

	public boolean validatedBookId(int id) throws LMSException {
		String idRegEx = "[1-9]{1}[0-9]{3}";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LMSException("Id should contains exactly 4  digits");
		}
		return result;
	}

	public boolean validatedYear(int date) throws LMSException {
		String regex = "199[0-9]|200[0-9]|201[0-9]|2020";
		boolean result = false;

		if (Pattern.matches(regex, String.valueOf(date))) {
			result = true;
		} else {
			throw new LMSException("Date should be between 1990-2020");
		}
		return result;
	}

	public boolean validatedISBN(long id) throws LMSException {
		String idRegEx = "[1-9]{10}";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LMSException("Id should contains exactly 10 digits");
		}
		return result;
	}

	public boolean validatedNum(int id) throws LMSException {
		String idRegEx = "^(?:[1-9]|0[1-9]|10|[0-9]{2})$";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LMSException("Num should contain atleast 1");
		}
		return result;
	}

	public boolean validatedStatus(String status) throws LMSException {
		String statusRegEx = "^(?i)(old|new)$";
		boolean result = false;
		Pattern pattern = Pattern.compile(statusRegEx);
		Matcher matcher = pattern.matcher(status);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Status should  contains only old/new");
		}
		return result;
	}
}
