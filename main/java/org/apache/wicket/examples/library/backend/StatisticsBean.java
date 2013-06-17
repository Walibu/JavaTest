/*
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * HSR Rapperswil
 * 
 * Thomas Briner, thomas.briner@gmail.com
 * 
 */
package org.apache.wicket.examples.library.backend;

import org.apache.wicket.examples.library.dao.Book;

public class StatisticsBean {
	private Integer nofBooks = null;
	private Double percentOfBooksIsFictional = null;
	private Book bookWithLongestName = null;

	// private Set<WritingStyleBookSet> writingStyleBookList = null;

	public Integer getNofBooks() {
		return nofBooks;
	}

	public void setNofBooks(Integer nofBooks) {
		this.nofBooks = nofBooks;
	}

	public Double getPercentOfBooksIsFictional() {
		return percentOfBooksIsFictional;
	}

	public void setPercentOfBooksIsFictional(Double percentOfBooksIsFictional) {
		this.percentOfBooksIsFictional = percentOfBooksIsFictional;
	}

	public Book getBookWithLongestName() {
		return bookWithLongestName;
	}

	public void setBookWithLongestName(Book bookWithLongestName) {
		this.bookWithLongestName = bookWithLongestName;
	}

	@Override
	public String toString() {
		return "StatisticsBean [nofBooks=" + nofBooks
				+ ", percentOfBooksIsFictional=" + percentOfBooksIsFictional
				+ ", bookWithLongestName=" + bookWithLongestName
				// + ", writingStyleBookList=" + writingStyleBookList
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((bookWithLongestName == null) ? 0 : bookWithLongestName
						.hashCode());
		result = prime * result
				+ ((nofBooks == null) ? 0 : nofBooks.hashCode());
		result = prime
				* result
				+ ((percentOfBooksIsFictional == null) ? 0
						: percentOfBooksIsFictional.hashCode());
		// result = prime
		// * result
		// + ((writingStyleBookList == null) ? 0 : writingStyleBookList
		// .hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticsBean other = (StatisticsBean) obj;
		if (bookWithLongestName == null) {
			if (other.bookWithLongestName != null)
				return false;
		} else if (!bookWithLongestName.equals(other.bookWithLongestName))
			return false;
		if (nofBooks == null) {
			if (other.nofBooks != null)
				return false;
		} else if (!nofBooks.equals(other.nofBooks))
			return false;
		if (percentOfBooksIsFictional == null) {
			if (other.percentOfBooksIsFictional != null)
				return false;
		} else if (!percentOfBooksIsFictional
				.equals(other.percentOfBooksIsFictional))
			return false;
		// if (writingStyleBookList == null) {
		// if (other.writingStyleBookList != null)
		// return false;
		// } else if (!writingStyleBookList.equals(other.writingStyleBookList))
		// return false;
		return true;
	}

}

// class WritingStyleBookSet {
// private WritingStyle writingStyle;
// private List<Book> books = new ArrayList<Book>();
//
// public WritingStyleBookSet(WritingStyle writingStyle) {
// this.writingStyle = writingStyle;
// }
//
// public WritingStyle getWritingStyle() {
// return writingStyle;
// }
//
// public List<Book> getBooks() {
// return books;
// }
//
// public void addBook(Book book) {
// this.books.add(book);
// }
//
// @Override
// public String toString() {
// return "WritingStyleBookList [writingStyle=" + writingStyle
// + ", books=" + books + "]";
// }
//
// @Override
// public int hashCode() {
// final int prime = 31;
// int result = 1;
// result = prime * result + ((books == null) ? 0 : books.hashCode());
// result = prime * result
// + ((writingStyle == null) ? 0 : writingStyle.hashCode());
// return result;
// }
//
// @Override
// public boolean equals(Object obj) {
// if (this == obj)
// return true;
// if (obj == null)
// return false;
// if (getClass() != obj.getClass())
// return false;
// WritingStyleBookSet other = (WritingStyleBookSet) obj;
// if (books == null) {
// if (other.books != null)
// return false;
// } else if (!books.equals(other.books))
// return false;
// if (writingStyle == null) {
// if (other.writingStyle != null)
// return false;
// } else if (!writingStyle.equals(other.writingStyle))
// return false;
// return true;
// }
//
// public int compareTo(WritingStyleBookSet o) {
// if (this.writingStyle.toString().compareTo(o.writingStyle.toString()) != 0) {
// return this.writingStyle.toString().compareTo(
// o.writingStyle.toString());
// }
// Iterator<Book> it = this.books.iterator();
// while (it.hasNext()) {
// if (!o.books.contains(it.next())) {
// return 1;
// }
// }
// it = o.books.iterator();
// while (it.hasNext()) {
// if (!this.books.contains(it.next())) {
// return -1;
// }
// }
// return 0;
// }
//
// }
