package com.library.Service;

import com.library.Model.Author;

public interface AuthorService {
    Iterable<Author> getAll();

    Author save(Author author);

    Author getAuthorById(Long id);

    Author getAuthorByName(String name);

    void deleteAuthor(Long id);
}
