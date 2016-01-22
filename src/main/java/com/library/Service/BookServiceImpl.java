package com.library.Service;

import com.library.Model.Author;
import com.library.Model.Book;
import com.library.Model.Magazine;
import com.library.Model.PublicationWorkGenre;
import com.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Iterable<Book> getPage(int page, int pageSize) {
        Pageable pageable = new PageRequest(0, pageSize, new Sort(Sort.Direction.DESC, "id"));
        return this.bookRepository.findAll(pageable).getContent();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Book updateBook(Book book) {
        // update attributes
        return null;
    }

    @Override
    public Iterable<Book> getByAuthor(Author author, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.bookRepository.getByAuthor(author, pageable);
    }

    @Override
    public Iterable<Book> getByGenre(PublicationWorkGenre genre, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.bookRepository.getByGenre(genre, pageable);
    }
}
