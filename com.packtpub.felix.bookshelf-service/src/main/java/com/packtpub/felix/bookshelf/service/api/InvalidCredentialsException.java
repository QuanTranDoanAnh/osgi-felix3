package com.packtpub.felix.bookshelf.service.api;

public class InvalidCredentialsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1778181084812038689L;

	public InvalidCredentialsException(String username) {
		super("Invalid Credentials: " + username);
	}
}
