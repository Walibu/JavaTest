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

import org.apache.wicket.examples.library.global.LibraryException;

public interface IStatisticsPublisher {

	public void publish(StatisticsBean statistics) throws LibraryException;

	public URL getPublishedURL() throws LibraryException;

}
