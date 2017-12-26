package com.packtpub.felix.bookshelf.service.impl;

public class SessionNotValidRuntimeException extends Exception {

	public SessionNotValidRuntimeException(String sessionId) {
		super("Invalid session: " + sessionId);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5055821605780551907L;

}
