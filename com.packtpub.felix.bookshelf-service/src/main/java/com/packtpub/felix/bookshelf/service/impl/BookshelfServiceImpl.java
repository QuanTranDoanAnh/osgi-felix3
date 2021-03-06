package com.packtpub.felix.bookshelf.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.packtpub.felix.bookshelf.inventory.api.Book;
import com.packtpub.felix.bookshelf.inventory.api.BookAlreadyExistsException;
import com.packtpub.felix.bookshelf.inventory.api.BookInventory;
import com.packtpub.felix.bookshelf.inventory.api.BookInventory.SearchCriteria;
import com.packtpub.felix.bookshelf.inventory.api.BookNotFoundException;
import com.packtpub.felix.bookshelf.inventory.api.InvalidBookException;
import com.packtpub.felix.bookshelf.inventory.api.MutableBook;
import com.packtpub.felix.bookshelf.service.api.BookshelfService;
import com.packtpub.felix.bookshelf.service.api.InvalidCredentialsException;

public class BookshelfServiceImpl implements BookshelfService {

	private String sessionId;
	private BundleContext context;
	
	public BookshelfServiceImpl(BundleContext context) {
		this.context = context;
	}
	
	@Override
	public String login(String username, char[] password) throws InvalidCredentialsException {
		if ("admin".equals(username) && Arrays.equals(password, "admin".toCharArray())) {
			this.sessionId = Long.toString(System.currentTimeMillis());
			return this.sessionId;
		}
		throw new InvalidCredentialsException(username);
	}

	@Override
	public void logout(String sessionId) throws SessionNotValidRuntimeException {
		checkSession(sessionId);
		this.sessionId = null;
	}

	protected void checkSession(String sessionId) throws SessionNotValidRuntimeException {
		if (!sessionIsValid(sessionId)) {
			throw new SessionNotValidRuntimeException(sessionId);
		}
	}

	@Override
	public boolean sessionIsValid(String sessionId) {
		return this.sessionId != null && this.sessionId.equals(sessionId);
	}

	@Override
	public Set<String> getCategories(String sessionId) throws BookInventoryNotRegisteredRuntimeException, SessionNotValidRuntimeException {
		checkSession(sessionId);
		BookInventory bookInventory = lookupBookInventory();
		return bookInventory.getCategories();
	}

	@Override
	public void addBook(String session, String isbn, String title, String author, String category, int rating)
			throws BookAlreadyExistsException, InvalidCredentialsException, BookInventoryNotRegisteredRuntimeException, SessionNotValidRuntimeException, InvalidBookException {
		checkSession(session);
        
        BookInventory inv = lookupBookInventory();
        
        MutableBook book = inv.createBook(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setRating(rating);
        
        inv.storeBook(book);

	}

	@Override
	public void modifyBookCategory(String session, String isbn, String category)
			throws BookNotFoundException, InvalidCredentialsException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException, InvalidBookException {
		checkSession(session);
        
        BookInventory inv = lookupBookInventory();
        
        MutableBook book = inv.loadBookForEdit(isbn);
        book.setCategory(category);
        
        inv.storeBook(book);

	}

	@Override
	public void removeBook(String session, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
		checkSession(session);
        BookInventory inv = lookupBookInventory();
        inv.removeBook(isbn);

	}

	@Override
	public Book getBook(String sessionId, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
		checkSession(sessionId);
		BookInventory bookInventory = lookupBookInventory();
		return bookInventory.loadBook(isbn);
	}

	private BookInventory lookupBookInventory() throws BookInventoryNotRegisteredRuntimeException {
		String name = BookInventory.class.getName();
		ServiceReference<?> ref = this.context.getServiceReference(name);
		if (ref == null) {
			throw new BookInventoryNotRegisteredRuntimeException(name);
		}
		return (BookInventory) this.context.getService(ref);
	}

	@Override
	public Set<String> searchBooksByCategory(String sessionId, String categoryLike)
			throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
		checkSession(sessionId);
        BookInventory inv = lookupBookInventory();
        Map<SearchCriteria, String> crits = new HashMap<SearchCriteria, String>();
        crits.put(SearchCriteria.CATEGORY_LIKE, categoryLike);
        return inv.searchBooks(crits);
	}

	@Override
	public Set<String> searchBooksByAuthor(String sessionId, String authorLike)
			throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
		checkSession(sessionId);
        BookInventory inv = lookupBookInventory();
        Map<SearchCriteria, String> crits = new HashMap<SearchCriteria, String>();
        crits.put(SearchCriteria.AUTHOR_LIKE, authorLike);
        return inv.searchBooks(crits);
	}

	@Override
	public Set<String> searchBooksByTitle(String sessionId, String titleLike)
			throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
		checkSession(sessionId);
        BookInventory inv = lookupBookInventory();
        Map<SearchCriteria, String> crits = new HashMap<SearchCriteria, String>();
        crits.put(SearchCriteria.TITLE_LIKE, titleLike);
        return inv.searchBooks(crits);
	}

	@Override
	public Set<String> searchBooksByRating(String sessionId, int ratingLower, int ratingUpper)
			throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
		checkSession(sessionId);
        BookInventory inv = lookupBookInventory();
        Map<SearchCriteria, String> crits = new HashMap<SearchCriteria, String>();
        crits.put(SearchCriteria.RATING_LT, Integer.toString(ratingLower));
        crits.put(SearchCriteria.RATING_GT, Integer.toString(ratingUpper));
        return inv.searchBooks(crits);
	}
	
	public MutableBook getBookForEdit(String session, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
        checkSession(session);
        BookInventory inv = lookupBookInventory();
        return inv.loadBookForEdit(isbn);
    }

	@Override
	public void startedBook(String sessionId, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishedBook(String session, String isbn, int rating)
			throws BookNotFoundException, SessionNotValidRuntimeException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getNotStartedBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getUnfinishedBooks() {
		// TODO Auto-generated method stub
		return null;
	}

}
