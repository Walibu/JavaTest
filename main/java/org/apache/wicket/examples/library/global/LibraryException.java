package org.apache.wicket.examples.library.global;

public class LibraryException extends Exception {

	private static final long serialVersionUID = 1L;

	public LibraryException() {
		super();
	}

	public LibraryException(String message) {
		super(message);
	}

	public LibraryException(String message, Throwable cause) {
		super(message, cause);
	}

	public LibraryException(Throwable cause) {
		super(cause);
	}

}
