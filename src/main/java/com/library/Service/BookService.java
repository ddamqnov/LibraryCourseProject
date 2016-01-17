package com.library.Service;

import com.library.Model.Book;

/**
 * Created by D on 16.1.2016 г..
 */

public interface BookService {
    Iterable<Book> getAll();

    Book save(Book book);

    Book getBookById(Long id);

    void deleteBook(Long id);

    Book updateBook(Book book);
}
