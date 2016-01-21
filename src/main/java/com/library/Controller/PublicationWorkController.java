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
            Book newBook = this.mapPublicationWorkModelToBook(publicationWork);

            newBook = bookService.save(newBook);

            publicationWork.setId(newBook.getId());
        } else {
            Magazine newMagazine = this.mapPublicationWorkModelToMagazine(publicationWork);

            newMagazine = magazineService.save(newMagazine);

            publicationWork.setId(newMagazine.getId());
        }

        return publicationWork;
    }

    @RequestMapping(value = "/publicationwork", method = RequestMethod.PUT)
    public PublicationWorkModel update(PublicationWorkModel publicationWork) {
        if (publicationWork.getType() == PublicationWorkType.BOOK) {
            Book book = this.mapPublicationWorkModelToBook(publicationWork);

            bookService.updateBook(book);
        } else {
            Magazine magazine = this.mapPublicationWorkModelToMagazine(publicationWork);

            magazineService.updateMagazine(magazine);
        }

        return publicationWork;
    }

    private Book mapPublicationWorkModelToBook(PublicationWorkModel publicationWork) {
        Book book = new Book();

        book.setGenre(publicationWork.getGenre());
        book.setPages(publicationWork.getPages());
        book.setTitle(publicationWork.getTitle());
        book.setPublicationDate(publicationWork.getPublicationDate());
        book.setAuthors(this.getAuthors(publicationWork.getAuthors()));
        book.setGenre(publicationWork.getGenre());

        return book;
    }

    private Magazine mapPublicationWorkModelToMagazine(PublicationWorkModel publicationWork) {
        Magazine magazine = new Magazine();

        magazine.setGenre(publicationWork.getGenre());
        magazine.setPages(publicationWork.getPages());
        magazine.setTitle(publicationWork.getTitle());
        magazine.setPublicationDate(publicationWork.getPublicationDate());
        magazine.setAuthors(this.getAuthors(publicationWork.getAuthors()));
        magazine.setGenre(publicationWork.getGenre());
        magazine.setIssue(publicationWork.getIssue());

        return magazine;
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
