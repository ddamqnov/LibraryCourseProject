package com.library.Service;

import com.library.Model.Book;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Iterable<Book> getAll();

    Iterable<Book> getPage(Pageable pageable);

    Book save(Book book);

    Book getBookById(Long id);

    void deleteBook(Long id);

    Book updateBook(Book book);
}
