package com.capgemini.librarymanagementsystemspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;
import com.capgemini.librarymanagementsystemspring.dto.LMSResponse;
import com.capgemini.librarymanagementsystemspring.dto.UserBean;
import com.capgemini.librarymanagementsystemspring.service.AdminService;
import com.capgemini.librarymanagementsystemspring.service.StudentService;
import com.capgemini.librarymanagementsystemspring.service.UserService;

public class Controller {
	@Autowired
	UserService service;
	@Autowired
	AdminService service1;
	@Autowired
	StudentService service2;

	@PostMapping(path = "/addUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public LMSResponse addUser(@RequestBody UserBean bean) {
		boolean isAdded = service.register(bean);
		LMSResponse response = new LMSResponse();
		if (isAdded) {
			response.setMessage("record inserted");
		} else {
			response.setError(true);
			response.setMessage("unable to add");
		}
		return response;
	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LMSResponse authentication(@RequestBody String email, String password) {
		UserBean userLogin = service.login(email, password);
		LMSResponse response = new LMSResponse();
		if (userLogin != null) {
			response.setMessage("Login succesful");
		} else {
			response.setError(true);
			response.setMessage("Cannot login");
		}
		return response;
	}

	@PostMapping(path = "/addBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LMSResponse addBook(@RequestBody BookBean bean) {
		boolean isBookAdded = service1.addBook(bean);
		LMSResponse response = new LMSResponse();
		if (isBookAdded) {
			response.setMessage("Book added succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be added");
		}
		return response;

	}

	@PutMapping(path = "/bookUpdate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LMSResponse updateBook(@RequestBody BookBean bean) {
		boolean isBookUpdated = service1.update(bean);
		LMSResponse response = new LMSResponse();
		if (isBookUpdated) {
			response.setMessage("Book updated succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be updated");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteBook/{bookId} ", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LMSResponse deleteBook(@PathVariable(name = "bookId") int bId) {
		boolean isBookDeleted = service1.delete(bId);
		LMSResponse response = new LMSResponse();
		if (isBookDeleted) {
			response.setMessage("Book deleted succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be deleted");
		}
		return response;
	}

	@GetMapping(path = "/getBookId", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LMSResponse getBookIds() {
		List<Integer> getId = service1.getBookIds();
		LMSResponse response = new LMSResponse();
		if (getId != null && !getId.isEmpty()) {
			response.setMessage("Book id found");
			response.setBookBeanId(getId);
		} else {
			response.setError(true);
			response.setMessage("No id found");
		}
		return response;
	}

	@GetMapping(path = "/getBookInfo", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LMSResponse getBookInfo() {
		List<BookBean> getInfo = service1.getBooksInfo();
		LMSResponse response = new LMSResponse();
		if (getInfo != null && !getInfo.isEmpty()) {
			response.setMessage("Book info found");
			response.setBookBeanList(getInfo);
		} else {
			response.setError(true);
			response.setMessage("No info found in db");
		}
		return response;
	}

	@GetMapping(path = "/getBookByName", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LMSResponse getBookByName(String bookTitle) {
		BookBean bean = service1.searchBookByTitle(bookTitle);
		LMSResponse response = new LMSResponse();
		if (bean != null) {
			response.setMessage("Book info found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("No info found in db");
		}
		return response;
	}

	@GetMapping(path = "/getBookByAuthor", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LMSResponse getBookByAuthor(String author) {
		BookBean bean = service1.searchBookByAuthor(author);
		LMSResponse response = new LMSResponse();
		if (bean != null) {
			response.setMessage("Book info found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("No info found in db");
		}
		return response;
	}

	@GetMapping(path = "/getBookById", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LMSResponse getBookById(int bId) {
		BookBean bean = service1.searchBookById(bId);
		LMSResponse response = new LMSResponse();
		if (bean != null) {
			response.setMessage("Book info found");
			response.setBook(bean);
		} else {
			response.setError(true);
			response.setMessage("No info found in db");
		}
		return response;
	}

	@GetMapping(path = "/issueBook/{userId}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LMSResponse issueBook(@PathVariable(name = "userId") int id, @PathVariable(name = "bookId") int bId) {
		boolean isBookIssued = service1.issueBook(id, bId);
		LMSResponse response = new LMSResponse();
		if (isBookIssued) {
			response.setMessage("Book issued succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be issued");
		}
		return response;
	}

	@GetMapping(path = "/returnBook/{bookId}/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LMSResponse returnBook(@PathVariable(name = "bookId") int bId, @PathVariable(name = "userId") int id) {
		boolean isBookReturned = service1.returnBook(bId, id);
		LMSResponse response = new LMSResponse();
		if (isBookReturned) {
			response.setMessage("Book returned succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be returned");
		}
		return response;
	}

	@GetMapping(path = "/requestBook/{userId}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LMSResponse requestBook(@PathVariable(name = "userId") int id, @PathVariable(name = "bookId") int bId) {
		boolean isBookRequested = service2.request(id, bId);
		LMSResponse response = new LMSResponse();
		if (isBookRequested) {
			response.setMessage("Book requested succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be requested");
		}
		return response;
	}

	@GetMapping(path = "/returnRequestBook/{userId}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LMSResponse returnRequestBook(@PathVariable(name = "userId") int id,
			@PathVariable(name = "bookId") int bId) {
		boolean isBookReturnRequested = service2.reqReturnBook(id, bId);
		LMSResponse response = new LMSResponse();
		if (isBookReturnRequested) {
			response.setMessage("Book return requested succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot place return request");
		}
		return response;
	}

}
