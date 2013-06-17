/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.examples.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.IClusterable;
import org.apache.wicket.util.lang.EnumeratedType;

/**
 * An example POJO model.
 * 
 * @author Jonathan Locke
 */
public final class Book implements IClusterable, Comparable<Book> {
	/**
	 * Value for fiction books.
	 */
	public static final boolean FICTION = true;

	/**
	 * Value for non-fiction books.
	 */
	public static final boolean NON_FICTION = false;

	/** Funny book */
	public static final WritingStyle FUNNY = new WritingStyle("funny");

	/** Boring book */
	public static final WritingStyle BORING = new WritingStyle("boring");

	/** Sad book */
	public static final WritingStyle SAD = new WritingStyle("sad");

	/** Bad book */
	public static final WritingStyle BAD = new WritingStyle("bad");

	static {
		new Book("Cat in Hat", "Dr. Seuss", Book.FICTION);
		new Book("That is Highly Illogical", "Dr. Spock", Book.NON_FICTION);
		new Book("Where's my Tardis, dude?", "Dr. Who", Book.FICTION);
	}

	// These variables are public in order to use fit rowfixture
	public long id;
	public String title;
	public String author;
	private Book companionBook;
	private Book relatedBook;
	private boolean isFiction;
	private List<WritingStyle> writingStyles = new ArrayList<WritingStyle>();

	/**
	 * Constructor
	 * 
	 * @param title
	 *            Book title
	 * @param author
	 *            The author of the book
	 * @param isFiction
	 *            True (FICTION) if the book is fiction, false (NON_FICTION) if
	 *            it is not.
	 */
	public Book(final String title, final String author, final boolean isFiction) {
		this.id = -1;
		this.title = title;
		this.author = author;
		this.isFiction = isFiction;
	}

	/**
	 * @return Book id
	 */
	public final long getId() {
		return id;
	}

	/**
	 * @param id
	 *            New id
	 */
	public final void setId(final long id) {
		this.id = id;
	}

	/**
	 * @return The author
	 */
	public final String getAuthor() {
		return author;
	}

	/**
	 * @return The title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param string
	 */
	public final void setAuthor(final String string) {
		author = string;
	}

	/**
	 * @param string
	 */
	public final void setTitle(final String string) {
		title = string;
	}

	/**
	 * @return A book that makes a good companion to this one
	 */
	public final Book getCompanionBook() {
		return companionBook;
	}

	/**
	 * @param book
	 *            A book that makes a good companion to this one
	 */
	public final void setCompanionBook(final Book book) {
		companionBook = book;
	}

	/**
	 * @param isFiction
	 *            True if this book is fiction
	 */
	public final void setFiction(final boolean isFiction) {
		this.isFiction = isFiction;
	}

	/**
	 * @return True if this book is fiction
	 */
	public final boolean getFiction() {
		return isFiction;
	}

	/**
	 * @return Returns the writingStyles.
	 */
	public final List<WritingStyle> getWritingStyles() {
		return writingStyles;
	}

	/**
	 * @param writingStyles
	 *            The writingStyles to set.
	 */
	public final void setWritingStyles(final List<WritingStyle> writingStyles) {
		this.writingStyles = writingStyles;
	}

	/**
	 * @return Returns the relatedBook.
	 */
	public final Book getRelatedBook() {
		return relatedBook;
	}

	/**
	 * @param relatedBook
	 *            The relatedBook to set.
	 */
	public final void setRelatedBook(final Book relatedBook) {
		this.relatedBook = relatedBook;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return id + ": " + title + " (" + author + ")";
	}

	/**
	 * Typesafe enumeration for writing styles
	 */
	public static final class WritingStyle extends EnumeratedType {
		WritingStyle(final String name) {
			super(name);
		}
	}

	public int compareTo(Book other) {
		if (this.id != other.id) {
			if (this.id > other.id) {
				return 1;
			} else {
				return 0;
			}
		}
		if (this.title.compareTo(other.title) != 0) {
			return this.title.compareTo(other.title);
		}
		if (this.author.compareTo(other.author) != 0) {
			return this.author.compareTo(other.author);
		}
		return 0;
	}
}
