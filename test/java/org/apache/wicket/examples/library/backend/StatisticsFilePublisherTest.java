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

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.examples.library.global.LibraryException;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class StatisticsFilePublisherTest {

	private IStatisticsPublisher publisher;

	@Before
	public void setUp() throws IOException {
		StatisticsFilePublisher statFilePublisher = new StatisticsFilePublisher();
		statFilePublisher.removeStatisticsFile();
		this.publisher = statFilePublisher;
	}

	@Test
	public void testWriteNewFile() throws LibraryException, IOException {
		StatisticsBean expected = new StatisticsBean();
		expected.setNofBooks(2);

		publisher.publish(expected);

		XStream xStream = new XStream();
		StatisticsBean written = (StatisticsBean) xStream.fromXML(FileUtils
				.readFileToString(new File(StatisticsFilePublisher.reportDir,
						StatisticsFilePublisher.report)));
		Assert.assertEquals(expected, written);
	}

	@Test
	public void testOverwriteExistingFile() throws LibraryException,
			IOException {
		IStatisticsPublisher publisher = new StatisticsFilePublisher();
		StatisticsBean expected = new StatisticsBean();
		expected.setNofBooks(2);
		publisher.publish(expected);

		XStream xStream = new XStream();
		StatisticsBean written = (StatisticsBean) xStream.fromXML(FileUtils
				.readFileToString(new File(StatisticsFilePublisher.reportDir,
						StatisticsFilePublisher.report)));
		Assert.assertEquals(expected, written);

		expected.setNofBooks(3);
		expected.setPercentOfBooksIsFictional(0.75);
		publisher.publish(expected);

		written = (StatisticsBean) xStream.fromXML(FileUtils
				.readFileToString(new File(StatisticsFilePublisher.reportDir,
						StatisticsFilePublisher.report)));
		Assert.assertEquals(expected, written);

	}

	@Test
	public void testAddToExistingFile() throws LibraryException, IOException {
		IStatisticsPublisher publisher = new StatisticsFilePublisher();
		StatisticsBean expected = new StatisticsBean();
		expected.setNofBooks(2);
		publisher.publish(expected);

		XStream xStream = new XStream();
		StatisticsBean written = (StatisticsBean) xStream.fromXML(FileUtils
				.readFileToString(new File(StatisticsFilePublisher.reportDir,
						StatisticsFilePublisher.report)));
		Assert.assertEquals(expected, written);

		expected.setNofBooks(null);
		expected.setPercentOfBooksIsFictional(0.75);
		publisher.publish(expected);

		written = (StatisticsBean) xStream.fromXML(FileUtils
				.readFileToString(new File(StatisticsFilePublisher.reportDir,
						StatisticsFilePublisher.report)));
		// from the first call
		expected.setNofBooks(2);
		Assert.assertEquals(expected, written);

	}

}
