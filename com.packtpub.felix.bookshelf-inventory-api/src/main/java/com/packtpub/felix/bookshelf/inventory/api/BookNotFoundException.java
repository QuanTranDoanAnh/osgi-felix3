package com.packtpub.felix.bookshelf.inventory.api;

public class BookNotFoundException extends Exception {

	public BookNotFoundException(String isbn) {
		super("Cannot find a book with ISBN " + isbn);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 810473040907417871L;

}
