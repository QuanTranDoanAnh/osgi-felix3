package com.packtpub.felix.bookshelf.inventory.impl.mock;

import java.net.URL;
import java.util.Date;

import com.packtpub.felix.bookshelf.inventory.api.MutableBook;

public class MutableBookImpl implements MutableBook {
	private String isbn;
	private String title;
	private String author;
	private String category;
	private int rating;
	private Date startDate;
	private Date finishDate;
	private URL frontCover;
	
	public MutableBookImpl(String isbn) {
		setIsbn(isbn);
	}
	
	@Override
	public String getIsbn() {
		return this.isbn;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getAuthor() {
		return this.author;
	}

	@Override
	public String getCategory() {
		return this.category;
	}

	@Override
	public int getRating() {
		return this.rating;
	}

	@Override
	public Date getStartDate() {
		return this.startDate;
	}

	@Override
	public Date getFinishDate() {
		return this.finishDate;
	}

	@Override
	public URL getFrontCover() {
		return this.frontCover;
	}

	@Override
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	@Override
	public void setFrontCover(URL frontCover) {
		this.frontCover = frontCover;
	}
	
	@Override
	public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(getCategory()).append(": ");
        buf.append(getTitle()).append(" from ").append(getAuthor());
        buf.append(" [").append(getRating()).append(']');
        return buf.toString();
    }
}
