package com.library.Service;

import com.library.Model.Author;
import com.library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Iterable<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return this.authorRepository.findOne(id);
    }

    @Override
    public Author getAuthorByName(String name) {
        List<Author> authors = this.authorRepository.getAuthorByName(name);

        if(authors.isEmpty()){
            return null;
        } else {
            return authors.get(0);
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        this.authorRepository.delete(id);
    }
}
