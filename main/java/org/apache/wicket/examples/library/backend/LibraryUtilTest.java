package org.apache.wicket.examples.library.backend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.examples.library.dao.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LibraryUtilTest {
	Book book1 = null;
	List<Book> books = null;

	@Before
	public void setUp() throws Exception {
		book1 = new Book("Cat in Hat", "Dr. Seuss", Book.FICTION);
		books = new ArrayList<Book>();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetNextFreeId() {
		book1.setId(1);
		books.add(book1);

		assertEquals("Only on book, test next ID", 2,
				LibraryUtil.getNextFreeId(books));
	}

	@Test
	public void testGetNextFreeIdOnEmpty() {

		assertEquals("No books in list, test next ID", 0,
				LibraryUtil.getNextFreeId(books));
	}

	@Test
	public void testAlreadyExists() {
		book1.setId(1);
		books.add(book1);

		assertEquals("Test title and author", true,
				LibraryUtil.alreadyExists(books, "Cat in Hat", "Dr. Seuss"));

		assertEquals("Test title and author", false,
				LibraryUtil.alreadyExists(books, "Cat in Hat", ""));
	}
}
