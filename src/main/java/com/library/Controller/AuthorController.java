package com.library.Controller;

import com.library.Model.Author;
import com.library.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by D on 16.1.2016 г..
 */

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public Iterable<Author> getAll() {
        return authorService.getAll();
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public Author getBook(@PathVariable long id) {
        return authorService.getAuthorById(id);
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public void save(@RequestBody Author author) {
        authorService.save(author);
    }

    @RequestMapping(value = "/author", method = RequestMethod.PUT)
    public void delete(@RequestBody Author author) {
        authorService.updateAuthor(author);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

}
