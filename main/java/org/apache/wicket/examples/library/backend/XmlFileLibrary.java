/*
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * HSR Rapperswil
 * 
 * Thomas Briner, thomas.briner@gmail.com
 * 
 */
package org.apache.wicket.examples.library.backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.examples.library.dao.Book;
import org.apache.wicket.examples.library.global.LibraryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

public class XmlFileLibrary implements ILibraryPersister {

	private static final Logger log = LoggerFactory
			.getLogger(XmlFileLibrary.class);

	private List<Book> books;
	private File library = new File("books.xml");

	public XmlFileLibrary() throws LibraryException {
		init();
	}

	public Book addBook(String title, String author) throws LibraryException {
		log.info("add Book with title '" + title + "' and author '" + author
				+ "' to library");
		if (LibraryUtil.alreadyExists(books, title, author)) {
			throw new LibraryException("Book with title '" + title
					+ "' and author '" + author
					+ "' already exists in library!");
		} else {
			long id = LibraryUtil.getNextFreeId(books);
			Book newBook = new Book(title, author, false);
			newBook.setId(id);
			books.add(newBook);
			storeLibrary();
			return newBook;
		}
	}

	public void removeBook(Book book) throws LibraryException {
		log.info("remove Book with id " + book.getId());

		Book fromLibrary = LibraryUtil.getBookById(book.getId(), this.books);
		if (fromLibrary == null) {
			throw new LibraryException("Book with id '" + book.getId()
					+ "' does not exist in library!");
		} else {
			books.remove(fromLibrary);
			storeLibrary();
		}
	}

	public List<Book> getBooks() {
		log.debug("Books read from library");
		return books;
	}

	private void init() throws LibraryException {
		XStream xStream = new XStream();
		if (library.exists()) {
			try {
				books = (List<Book>) xStream.fromXML(FileUtils
						.readFileToString(library));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.books = new ArrayList<Book>();

			log.info("Creating library file from scratch");
			addBook("Test-Driven Development", "Kent Beck");
			addBook("Agile Testing", "Lisa Crispin, Janet Gregory");
			addBook("Effective Java", "Joshua Bloch");
			storeLibrary();

		}
	}

	private void storeLibrary() {
		XStream xStream = new XStream();
		String libraryXML = xStream.toXML(books);
		try {
			FileUtils.writeStringToFile(library, libraryXML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBook(Book book) throws LibraryException {
		Book fromLibrary = LibraryUtil.getBookById(book.getId(), this.books);
		if (fromLibrary == null) {
			throw new LibraryException("Book with id '" + book.getId()
					+ "' does not exist in library!");
		} else {
			books.remove(fromLibrary);
			if (LibraryUtil.alreadyExists(books, fromLibrary.getTitle(),
					fromLibrary.getAuthor())) {
				throw new LibraryException("Book with title '"
						+ fromLibrary.getTitle() + "' and author '"
						+ fromLibrary.getAuthor()
						+ "' already exists in library!");
			} else {
				books.add(book);
				storeLibrary();
			}
		}
	}

	public void resetLibrary() throws LibraryException {
		this.library.delete();
		this.init();

	}

	public Book getBookById(long id) {
		return LibraryUtil.getBookById(id, this.books);
	}

}
