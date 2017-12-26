package com.packtpub.felix.bookshelf.inventory.api;

import java.net.URL;
import java.util.Date;

public interface Book {

	String getIsbn();

	String getTitle();

	String getAuthor();

	String getCategory();

	int getRating();

	Date getStartDate();

	Date getFinishDate();

	URL getFrontCover();
}
