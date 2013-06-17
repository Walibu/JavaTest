/*
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * HSR Rapperswil
 * 
 * Thomas Briner, thomas.briner@gmail.com
 * 
 */
package org.apache.wicket.examples.library;

import org.apache.wicket.examples.library.backend.StatisticsFilePublisherTest;
import org.apache.wicket.examples.library.backend.StatisticsReporterTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ StatisticsFilePublisherTest.class,
		StatisticsReporterTest.class })
public class AllTests {

	public static void main(String[] args) {
		JUnitCore.main(AllTests.class.getName());
	}

}
