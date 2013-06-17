/*
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * HSR Rapperswil
 * 
 * Thomas Briner, thomas.briner@gmail.com
 * 
 */
package org.apache.wicket.examples.library.backend;

import java.net.URL;
import java.util.List;

import org.apache.wicket.examples.library.dao.Book;
import org.apache.wicket.examples.library.global.LibraryException;

/**
 * This class is used for publishing statistics about the current state of the
 * library. It uses an instance of a IStatisticsPublisher as output channel.
 * 
 * @author Thomas Briner, thomas.briner@gmail.com
 * 
 */
public class StatisticsReporter {

	/**
	 * Instance of the output channel. Is set during construction of this class.
	 */
	IStatisticsPublisher publisher = null;

	public StatisticsReporter(IStatisticsPublisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * Publish the number of books through the IStatisticsPublisher output
	 * channel. As a result the method returns the url of the generated
	 * statistics output.
	 * 
	 * @param books
	 * @return
	 * @throws LibraryException
	 */
	public URL publishNofBooks(List<Book> books) throws LibraryException {
		StatisticsBean statistics = new StatisticsBean();
		statistics.setNofBooks(books.size());
		try {
			publisher.publish(statistics);
		} catch (LibraryException e) {
			throw new LibraryException("Could not publish statistics!", e);
		}

		return publisher.getPublishedURL();

	}

	public URL publishBookWithLongestTitle(List<Book> books)
			throws LibraryException {
		StatisticsBean statistics = new StatisticsBean();
		Book bookWithLongestTitle = null;
		for (Book book : books) {
			if (bookWithLongestTitle == null
					|| book.getTitle().length() > bookWithLongestTitle
							.getTitle().length()) {
				bookWithLongestTitle = book;
			}
		}
		statistics.setBookWithLongestName(bookWithLongestTitle);
		try {
			publisher.publish(statistics);
		} catch (LibraryException e) {
			throw new LibraryException("Could not publish statistics!", e);
		}

		return publisher.getPublishedURL();
	}

	public URL publishPercentageOfFictionalBooks(List<Book> books)
			throws LibraryException {
		StatisticsBean statistics = new StatisticsBean();
		int nofFictionalBooks = 0;
		for (Book book : books) {
			if (book.getFiction()) {
				nofFictionalBooks++;
			}
		}
		statistics.setPercentOfBooksIsFictional((double) nofFictionalBooks
				/ books.size());
		try {
			publisher.publish(statistics);
		} catch (LibraryException e) {
			throw new LibraryException("Could not publish statistics!", e);
		}
		return publisher.getPublishedURL();

	}

}
