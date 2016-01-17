package com.library.Service;

import com.library.Model.Author;

/**
 * Created by D on 16.1.2016 г..
 */
public interface AuthorService {
    Iterable<Author> getAll();

    Author save(Author author);

    Author getAuthorById(Long id);

    void deleteAuthor(Long id);

    Author updateAuthor(Author author);
}
