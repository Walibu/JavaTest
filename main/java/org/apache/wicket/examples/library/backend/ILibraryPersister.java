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
import org.apache.wicket.examples.library.global.LibraryException;

public interface ILibraryPersister {

	/**
	 * Add a new book to the library
	 * 
	 * @param book
	 *            the book to be inserted in the library
	 * @throws LibraryException
	 *             if a book with the same title and author already exists
	 */
	public Book addBook(String title, String author) throws LibraryException;

	/**
	 * Remove a certain book from the library
	 * 
	 * @param book
	 *            the book to be removed
	 * @throws LibraryException
	 *             if this Book does not exist in the library
	 */
	public void removeBook(Book book) throws LibraryException;

	/**
	 * Modify attributes of a book in the library
	 * 
	 * @param book
	 *            the book with the new attributes
	 * 
	 * @throws LibraryException
	 *             if either a book with this title and author does not exist or
	 *             the book is now a duplicate (title and author) with another
	 *             book
	 */
	public void updateBook(Book book) throws LibraryException;

	/**
	 * Get a list of all books in the library
	 * 
	 * @return the list of books
	 */
	public List<Book> getBooks();

	/**
	 * Look for a certain book by its id
	 * 
	 * @param id
	 *            the id of the book
	 * @return the book if it is in the library, otherwise null
	 */
	public Book getBookById(long id);

	/**
	 * Reset the library for testing purposes to its initial content.
	 */
	public void resetLibrary() throws LibraryException;

}
