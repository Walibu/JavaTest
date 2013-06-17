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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.examples.library.dao.Book;
import org.apache.wicket.examples.library.global.AuthenticatedWebPage;
import org.apache.wicket.examples.library.global.LibraryException;
import org.apache.wicket.examples.library.service.LibraryService;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.lang.EnumeratedType;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * A page that contains a form that allows editing of books.
 * 
 * @author Jonathan Locke
 */
public final class AddBook extends AuthenticatedWebPage {
	static final Book otherBook = new Book("Frisbee Techniques",
			"Marty van Hoff", Book.FICTION);

	/**
	 * Constructs a page that edits a book
	 * 
	 * @param book
	 *            The book to edit
	 */
	public AddBook() {
		// Create and add feedback panel to page
		final FeedbackPanel feedback = new FeedbackPanel("feedback");

		add(feedback);

		// Add edit book form to page
		add(new AddBookForm("addBookForm", new Book("", "", false)));
	}

	/**
	 * Gets a link to a page that will edit a book
	 * 
	 * @param name
	 *            The name of the link
	 * @param id
	 *            The id of the book that the page will edit
	 * @return The page link
	 */
	public static StatelessLink link(final String name) {
		return new StatelessLink(name) {

			@Override
			public void onClick() {
				setResponsePage(AddBook.class);

			}
		};
		// , new IPageLink() {
		// public Page getPage() {
		// return new AddBook();
		// }
		//
		// public Class<? extends Page> getPageIdentity() {
		// return AddBook.class;
		// }
		// });
	}

	/**
	 * Form that edits a book
	 * 
	 * @author Jonathan Locke
	 */
	static public final class AddBookForm extends Form<Book> {
		/**
		 * Constructor
		 * 
		 * @param id
		 *            id of form
		 * @param book
		 *            Book model
		 */
		public AddBookForm(final String id, final Book book) {
			super(id, new CompoundPropertyModel<Book>(book));

			// Create a required text field with a max length of 30 characters
			// that edits the book's title
			final TextField<String> title = new TextField<String>("title");
			title.setRequired(true);
			title.add(StringValidator.maximumLength(30));
			final FormComponentFeedbackBorder titleFeedback = new FormComponentFeedbackBorder(
					"titleFeedback");
			add(titleFeedback);
			titleFeedback.add(title);

			// Create a required text field that edits the book's author
			final TextField<String> author = new TextField<String>("author");
			author.setRequired(true);
			final FormComponentFeedbackBorder authorFeedback = new FormComponentFeedbackBorder(
					"authorFeedback");
			add(authorFeedback);
			authorFeedback.add(author);

			// Add fiction checkbox
			add(new CheckBox("fiction"));

			// Books is everything but otherBook
			List<Book> books = new ArrayList<Book>();

			books.addAll(LibraryService.getInstance().getBooks());
			books.remove(otherBook);

			// Add companion book choice
			add(new DropDownChoice<Book>("companionBook", books));

			// Add radio choice test
			final RadioChoice<Book> relatedBook = new RadioChoice<Book>(
					"relatedBook", books);
			add(relatedBook);

			// Multi-select among writing styles
			add(new ListMultipleChoice<EnumeratedType>("writingStyles",
					EnumeratedType.getValues(Book.WritingStyle.class)));
		}

		/**
		 * Show the resulting valid edit
		 */
		@Override
		public final void onSubmit() {
			final RequestCycle cycle = getRequestCycle();
			System.err.println("submit");
			PageParameters parameters = new PageParameters();
			final Book book = getModelObject();

			try {
				Book newAdded = LibraryService.getInstance().addBook(book);
				parameters.put("id", newAdded.getId());
				cycle.setResponsePage(getSession().getPageFactory().newPage(
						BookDetails.class, parameters));
				cycle.setRedirect(true);
			} catch (LibraryException e) {
				parameters.put("exceptionMessage", e.getMessage());
				cycle.setResponsePage(getSession().getPageFactory().newPage(
						ErrorPage.class, parameters));
				cycle.setRedirect(true);
			}

		}
	}
}
