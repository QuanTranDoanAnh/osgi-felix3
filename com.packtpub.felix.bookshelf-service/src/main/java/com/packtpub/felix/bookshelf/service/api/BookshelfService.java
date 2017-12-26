package com.packtpub.felix.bookshelf.service.api;

import java.util.List;
import java.util.Set;

import com.packtpub.felix.bookshelf.inventory.api.Book;
import com.packtpub.felix.bookshelf.inventory.api.BookAlreadyExistsException;
import com.packtpub.felix.bookshelf.inventory.api.BookNotFoundException;
import com.packtpub.felix.bookshelf.inventory.api.InvalidBookException;
import com.packtpub.felix.bookshelf.inventory.api.MutableBook;
import com.packtpub.felix.bookshelf.service.impl.BookInventoryNotRegisteredRuntimeException;
import com.packtpub.felix.bookshelf.service.impl.SessionNotValidRuntimeException;

public interface BookshelfService extends Authentication {
	Set<String> getCategories(String sessionId) throws BookInventoryNotRegisteredRuntimeException, SessionNotValidRuntimeException;
	
	void addBook(
		String session, String isbn, String title,
		String author, String category, int rating)
	throws BookAlreadyExistsException, InvalidCredentialsException, BookInventoryNotRegisteredRuntimeException, SessionNotValidRuntimeException, InvalidBookException;
	
	void modifyBookCategory(
		String session, String isbn, String category)
	throws BookNotFoundException, InvalidCredentialsException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException, InvalidBookException;
	
	void removeBook(String sessionId, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;
	
	Book getBook(String sessionId, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;
	
	Set<String> searchBooksByCategory(String sessionId, String categoryLike) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;
	
	Set<String> searchBooksByAuthor(String sessionId, String authorLike) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;
	
	Set<String> searchBooksByTitle(String sessionId, String titleLike) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;
	
	Set<String> searchBooksByRating(String sessionId, int ratingLower, int ratingUpper) 
			throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;
	
	MutableBook getBookForEdit(String session, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;
	
	void startedBook(String sessionId, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException;
	
	void finishedBook(String sessionId, String isbn, int rating) throws BookNotFoundException, SessionNotValidRuntimeException;
	
	List<String> getNotStartedBooks();
	
	List<String> getUnfinishedBooks();
	
	
}
