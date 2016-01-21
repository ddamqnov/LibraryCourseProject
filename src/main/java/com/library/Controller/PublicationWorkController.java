package com.library.Controller;

import com.library.Dto.PublicationWorkModel;
import com.library.Dto.PublicationWorkType;
import com.library.Model.Author;
import com.library.Model.Book;
import com.library.Model.Magazine;
import com.library.Service.AuthorService;
import com.library.Service.BookService;
import com.library.Service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class PublicationWorkController {
    @Autowired
    private BookService bookService;

    @Autowired
    private MagazineService magazineService;

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/publicationwork", method = RequestMethod.POST)
    public PublicationWorkModel create(@RequestBody PublicationWorkModel publicationWork) {
        if (publicationWork.getType() == PublicationWorkType.BOOK) {
            Book newBook = publicationWork.toBook();
            newBook.setAuthors(this.getAuthors(publicationWork.getAuthors()));

            newBook = bookService.save(newBook);

            publicationWork.setId(newBook.getId());
        } else {
            Magazine newMagazine = publicationWork.toMagazine();
            newMagazine.setAuthors(this.getAuthors(publicationWork.getAuthors()));

            newMagazine = magazineService.save(newMagazine);

            publicationWork.setId(newMagazine.getId());
        }

        return publicationWork;
    }

    @RequestMapping(value = "/publicationwork", method = RequestMethod.PUT)
    public PublicationWorkModel update(PublicationWorkModel publicationWork) {
        if (publicationWork.getType() == PublicationWorkType.BOOK) {
            Book book = publicationWork.toBook();
            book.setAuthors(this.getAuthors(publicationWork.getAuthors()));

            bookService.updateBook(book);
        } else {
            Magazine magazine = publicationWork.toMagazine();
            magazine.setAuthors(this.getAuthors(publicationWork.getAuthors()));

            magazineService.updateMagazine(magazine);
        }

        return publicationWork;
    }

    private Set<Author> getAuthors(String mergedAuthors) {
        String[] splittedAuthors = mergedAuthors.split(",");

        Set<Author> authors = new HashSet<>();

        for (String authorName : splittedAuthors) {
            String trimmedAuthorName = authorName.trim();
            if (trimmedAuthorName.length() > 1) {
                Author author = this.authorService.getAuthorByName(trimmedAuthorName);

                if (author == null) {
                    author = new Author();
                    author.setName(trimmedAuthorName);

                    author = this.authorService.save(author);
                }

                authors.add(author);
            }
        }

        return authors;
    }
}
