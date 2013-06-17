package org.apache.wicket.examples.library.service;

import java.util.List;

import org.apache.wicket.examples.library.backend.ILibraryPersister;
import org.apache.wicket.examples.library.backend.StatisticsFilePublisher;
import org.apache.wicket.examples.library.backend.StatisticsReporter;
import org.apache.wicket.examples.library.dao.Book;
import org.apache.wicket.examples.library.global.LibraryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryService {

	private static LibraryService instance;
	private static ILibraryPersister library;
	private static StatisticsReporter statisticsReporter = new StatisticsReporter(
			new StatisticsFilePublisher());

	private static final Logger log = LoggerFactory
			.getLogger(LibraryService.class);

	public static void setPersisterClass(
			Class<? extends ILibraryPersister> clazz)
			throws InstantiationException, IllegalAccessException {
		library = clazz.newInstance();

	}

	public static LibraryService getInstance() {
		if (library == null) {
			throw new RuntimeException(
					"The LibraryService has not yet been initialized "
							+ "with an implementation of the ILibraryPersister!");
		}
		if (instance == null) {
			instance = new LibraryService();
		}
		return instance;
	}

	// prevent usage from without
	private LibraryService() {
	}

	public List<Book> getBooks() {
		return library.getBooks();
	}

	public void removeBook(final long id) throws LibraryException {
		Book book = library.getBookById(id);
		library.removeBook(book);

	}

	public Book addBook(Book book) throws LibraryException {
		Book newAdded = library.addBook(book.getTitle(), book.getAuthor());
		newAdded.setCompanionBook(book.getCompanionBook());
		newAdded.setRelatedBook(book.getRelatedBook());
		newAdded.setWritingStyles(book.getWritingStyles());
		library.updateBook(newAdded);
		return newAdded;
	}

	public void updateBook(Book book) throws LibraryException {
		library.updateBook(book);
	}

	public Book getBook(final long id) {
		return library.getBookById(id);
	}

	public void resetLibrary() throws LibraryException {
		library.resetLibrary();
	}

	public void generateStatistics() throws LibraryException {
		try {
			statisticsReporter.publishNofBooks(library.getBooks());
			statisticsReporter.publishBookWithLongestTitle(library.getBooks());
			statisticsReporter.publishPercentageOfFictionalBooks(library
					.getBooks());
		} catch (LibraryException e) {
			throw new LibraryException("Statistics could not be published!", e);
		}
	}
}
