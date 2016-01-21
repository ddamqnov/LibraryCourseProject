package com.library.Service;

import com.library.Model.Author;

public interface AuthorService {
    Iterable<Author> getAll();

    Author save(Author author);

    Author getAuthorById(Long id);

    void deleteAuthor(Long id);

    Author updateAuthor(Author author);
}
