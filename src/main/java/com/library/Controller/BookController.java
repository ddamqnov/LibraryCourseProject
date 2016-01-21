package com.library.Controller;

import com.library.Model.Book;
import com.library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by D on 16.1.2016 г..
 */

@RestController
@RequestMapping(value = "book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "last", method = RequestMethod.GET)
    public Page<Book> getLast(Pageable pageable) {
        return bookService.page(pageable);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Book getBook(@RequestParam long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Book getBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteBook(@RequestParam long id) {
        bookService.deleteBook(id);
    }

}
