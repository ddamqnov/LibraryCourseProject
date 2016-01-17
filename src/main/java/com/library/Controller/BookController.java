package com.library.Controller;

import com.library.Model.Book;
import com.library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by D on 16.1.2016 г..
 */

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Book getBook(@RequestParam long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book getBook(@RequestBody Book book) {
        return bookService.save(book);
    }


    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@RequestParam long id) {
        bookService.deleteBook(id);
    }

}
