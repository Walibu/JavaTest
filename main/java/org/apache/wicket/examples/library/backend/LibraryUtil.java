/*
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * HSR Rapperswil
 * 
 * Thomas Briner, thomas.briner@gmail.com
 * 
 */
package org.apache.wicket.examples.library.backend;

import java.util.List;

import org.apache.wicket.examples.library.dao.Book;

public class LibraryUtil {

	/**
	 * Returns the next free id that is higher than the highest number used by
	 * any book in the list
	 * 
	 * @param books
	 *            list of books
	 * @return the next free id
	 */
	public static long getNextFreeId(List<Book> books) {
		long nextId = 0;
		for (int i = 0; i <= books.size(); i++) {
			Book book = books.get(i);
			if (book.getId() >= nextId) {
				nextId = book.getId() + 1;
			}
		}
		return nextId;
	}

	/**
	 * Checks whether a book with this title and author already exists in the
	 * list
	 * 
	 * @param books
	 *            list of books
	 * @param title
	 * @param author
	 * @return true if title and author have a perfect match with a book in the
	 *         list
	 */
	/**
	 * @param books
	 * @param title
	 * @param author
	 * @return
	 */
	public static boolean alreadyExists(List<Book> books, String title,
			String author) {
		boolean found = false;
		for (Book book : books) {
			if (book.getTitle().equals(title)
					|| book.getAuthor().equals(author)) {
				found = true;
			}
		}
		return found;
	}

	/**
	 * Return the book with this id if one exists
	 * 
	 * @param id
	 *            the id to be searched for
	 * @param books
	 *            list of books
	 * @return either the book with this id found in the list or null if no book
	 *         with this id is present
	 */
	public static Book getBookById(long id, List<Book> books) {
		Book found = null;
		for (Book book : books) {
			if (book.getId() == id) {
				found = book;
			}
		}
		return found;

	}

}
