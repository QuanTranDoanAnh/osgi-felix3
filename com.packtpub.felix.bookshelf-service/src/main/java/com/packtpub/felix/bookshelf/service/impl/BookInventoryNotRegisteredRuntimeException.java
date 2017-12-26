package com.packtpub.felix.bookshelf.service.impl;

public class BookInventoryNotRegisteredRuntimeException extends Exception {

	public BookInventoryNotRegisteredRuntimeException(String name) {
		super("Book Inventory not registered with name: " + name);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5311117948950246021L;

}
