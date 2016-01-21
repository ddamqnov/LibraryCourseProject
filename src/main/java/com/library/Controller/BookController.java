package com.library.Controller;

import com.library.Model.Book;
import com.library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book/last/{count}", method = RequestMethod.GET)
    public Iterable<Book> getLast(@RequestParam int count) {
        Pageable pageable = new PageRequest(0, count, new Sort(Sort.Direction.DESC, "id"));
        return bookService.getPage(pageable).getContent();
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Book getBook(@RequestParam long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book createBook(@RequestBody Book book) {
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
