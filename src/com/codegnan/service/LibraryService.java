package com.codegnan.service;


import java.util.TreeSet;

import com.codegnan.dao.BookDAO;
import com.codegnan.model.Book;

public class LibraryService {
	private BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void updateBookRating(int ISBN, double rating) {
        bookDAO.updateBookRating(ISBN, rating);
    }

    public TreeSet<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getTopRatedBook() {
        return bookDAO.getTopRatedBook();
    }

	

}