package com.library.Service;

import com.library.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Iterable<Book> getAll();

    Page<Book> page(Pageable pageable);

    Book save(Book book);

    Book getBookById(Long id);

    void deleteBook(Long id);

    Book updateBook(Book book);
}
