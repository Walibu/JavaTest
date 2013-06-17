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
package org.apache.wicket.examples.library.pages;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.examples.library.dao.Book;
import org.apache.wicket.examples.library.global.AuthenticatedWebPage;
import org.apache.wicket.examples.library.global.LibraryException;
import org.apache.wicket.examples.library.service.LibraryService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.util.string.StringValueConversionException;

/**
 * A book details page. Shows information about a book.
 * 
 * @author Jonathan Locke
 */
public final class RemoveBook extends AuthenticatedWebPage {
	/**
	 * Constructor for calls from external page links
	 * 
	 * @param parameters
	 *            Page parameters
	 * @throws StringValueConversionException
	 */
	public RemoveBook(final PageParameters parameters)
			throws StringValueConversionException {
		this(LibraryService.getInstance().getBook(parameters.getLong("id")));
	}

	/**
	 * Constructor
	 * 
	 * @param book
	 *            The model
	 */
	public RemoveBook(final Book book) {
		add(new Label("title", book.getTitle()));
		add(new Label("author", book.getAuthor()));
		try {
			LibraryService.getInstance().removeBook(book.getId());
			add(new Label("errorMessage", ""));

		} catch (LibraryException e) {
			add(new Label("errorMessage", "Book could not be removed: "
					+ e.getMessage()));
		}
	}

	/**
	 * Creates an external page link
	 * 
	 * @param name
	 *            The name of the link component to create
	 * @param book
	 *            The book to link to
	 * @param noBookTitle
	 *            The title to show if book is null
	 * @return The external page link
	 */
	public static PageLink link(final String name, final long id) {
		return new PageLink(name, new IPageLink() {
			public Page getPage() {
				return new RemoveBook(LibraryService.getInstance().getBook(id));
			}

			public Class<? extends Page> getPageIdentity() {
				return RemoveBook.class;
			}
		});
	}
}
