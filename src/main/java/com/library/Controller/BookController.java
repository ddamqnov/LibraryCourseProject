package com.library.Controller;

import com.library.Dto.PublicationWorkSimpleResponseModel;
import com.library.Model.Book;
import com.library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book/last", method = RequestMethod.GET)
    public Iterable<PublicationWorkSimpleResponseModel> getLast(@RequestParam int count) {
        Pageable pageable = new PageRequest(0, count, new Sort(Sort.Direction.DESC, "id"));
        Iterable<Book> books = bookService.getPage(pageable);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Book book: books) {
            result.add(PublicationWorkSimpleResponseModel.fromBook(book));
        }

        return result;
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
