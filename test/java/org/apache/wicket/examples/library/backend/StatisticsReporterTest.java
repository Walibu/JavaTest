/*
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * HSR Rapperswil
 * 
 * Thomas Briner, thomas.briner@gmail.com
 * 
 */
package org.apache.wicket.examples.library.backend;

import static org.mockito.Mockito.mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.wicket.examples.library.dao.Book;
import org.apache.wicket.examples.library.global.LibraryException;
import org.junit.Test;
import org.mockito.Mockito;

public class StatisticsReporterTest {

	/**
	 * This is the fragment of a test case for the method publishNofBooks in the
	 * case when the library is empty.
	 * 
	 * @throws LibraryException
	 * @throws MalformedURLException
	 */
	@Test
	public void testNofBooksEmptyList() throws LibraryException,
			MalformedURLException {

		// create the mock object using the mockito framework
		IStatisticsPublisher mockedReport = mock(IStatisticsPublisher.class);
		Mockito.when(mockedReport.getPublishedURL()).thenReturn(
				new URL("file:///c:/temp.txt"));

		// create the object under test: The instance of the StatisticsReporter
		// with the mock object as constructor argument.
		StatisticsReporter reporter = new StatisticsReporter(mockedReport);

		// make the call to the method that you want to test
		List<Book> books = new ArrayList();

		URL testString = reporter.publishNofBooks(books);

		// create manually the StatisticsBean that you expect to be created by
		// the method under test and fill it with the information according to
		// this testcase
		StatisticsBean statistics = new StatisticsBean();
		statistics.setNofBooks(0);

		// verify that the object under test did the job:
		// ask the mock object if she received the call as expected
		Mockito.verify(mockedReport).publish(statistics);

		Assert.assertEquals(new URL("file:///c:/temp.txt"), testString);

	}
}
