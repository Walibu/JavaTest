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
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.examples.library.global.LibraryException;

import com.thoughtworks.xstream.XStream;

public class StatisticsFilePublisher implements IStatisticsPublisher {

	public static final File reportDir = new File(
			"../ch.hsr.mas.testing.systemundertest/reports");
	public static final String report = "report.xml";

	public void publish(StatisticsBean statistics) throws LibraryException {
		File reportFile = new File(reportDir, report);
		XStream xStream = new XStream();
		if (!reportFile.exists()) {

			if (!reportDir.exists()) {
				try {
					FileUtils.forceMkdir(reportDir);
				} catch (IOException e) {
					throw new LibraryException(
							"Could not create directory for report file!", e);
				}
			}
		} else {
			try {
				statistics = mergeStatisticBean(
						((StatisticsBean) xStream.fromXML(FileUtils
								.readFileToString(reportFile))), statistics);
			} catch (IOException e) {
				throw new LibraryException(
						"Could not parse the statistics file!", e);
			}
		}
		try {
			FileUtils.writeStringToFile(reportFile, xStream.toXML(statistics));
		} catch (IOException e) {
			throw new LibraryException(e);
		}

	}

	private StatisticsBean mergeStatisticBean(StatisticsBean fromFile,
			StatisticsBean fromLibrary) {
		StatisticsBean merged = fromFile;
		if (fromLibrary.getBookWithLongestName() != null) {
			merged.setBookWithLongestName(fromLibrary.getBookWithLongestName());
		}
		if (fromLibrary.getNofBooks() != null) {
			merged.setNofBooks(fromLibrary.getNofBooks());
		}
		if (fromLibrary.getPercentOfBooksIsFictional() != null) {
			merged.setPercentOfBooksIsFictional(fromLibrary
					.getPercentOfBooksIsFictional());
		}
		return merged;
	}

	public void removeStatisticsFile() throws IOException {
		FileUtils.deleteDirectory(StatisticsFilePublisher.reportDir);
	}

	public URL getPublishedURL() throws LibraryException {
		File reportFile = new File(reportDir, report);
		URL result = null;
		try {
			result = reportFile.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new LibraryException("Could not transform file path to URL.",
					e);
		}
		return result;
	}
}
