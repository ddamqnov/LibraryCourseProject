package com.library.Service;

import com.library.Model.Author;
import com.library.Model.Book;
import com.library.Model.PublicationWorkGenre;

public interface BookService {
    Iterable<Book> getAll();

    Iterable<Book> getPage(int page, int pageSize);

    Book save(Book book);

    Book getBookById(Long id);

    void deleteBook(Long id);

    Book updateBook(Book book);

    Iterable<Book> getByAuthor(Author author, int page, int pageSize);

    Iterable<Book> getByGenre(PublicationWorkGenre genre, int page, int pageSize);
}
