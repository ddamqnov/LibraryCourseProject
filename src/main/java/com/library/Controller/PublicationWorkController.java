package com.library.Controller;

import com.library.Dto.*;
import com.library.Model.Author;
import com.library.Model.Book;
import com.library.Model.Magazine;
import com.library.Model.PublicationWorkGenre;
import com.library.Service.AuthorService;
import com.library.Service.BookService;
import com.library.Service.MagazineService;
import com.library.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PublicationWorkController {
    private static final int MAX_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private BookService bookService;

    @Autowired
    private MagazineService magazineService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value = "/book/last", method = RequestMethod.GET)
    public Iterable<PublicationWorkSimpleResponseModel> getLastBooks(@RequestParam int count) {
        int pageSize = Math.min(count, MAX_PAGE_SIZE);

        Iterable<Book> books = this.bookService.getPage(0, pageSize);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Book book : books) {
            PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromBook(book);
            publicationWork.setRating(this.ratingService.getAverageRatingOfPublicationWork(book.getId()));
            result.add(publicationWork);
        }

        return result;
    }

    @RequestMapping(value = "/magazine/last", method = RequestMethod.GET)
    public Iterable<PublicationWorkSimpleResponseModel> getLastMagazines(@RequestParam int count) {
        int pageSize = Math.min(count, MAX_PAGE_SIZE);

        Iterable<Magazine> magazines = magazineService.getPage(0, pageSize);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Magazine magazine : magazines) {
            PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromMagazine(magazine);
            publicationWork.setRating(this.ratingService.getAverageRatingOfPublicationWork(magazine.getId()));
            result.add(publicationWork);
        }

        return result;
    }

    @RequestMapping(value = "/publicationwork", method = RequestMethod.GET)
    public PublicationWorkDetailsResponseModel get(@RequestParam long id, HttpServletRequest request) throws Exception {
        PublicationWorkDetailsResponseModel publicationWork = null;

        Book book = this.bookService.getBookById(id);

        if (book == null) {
            Magazine magazine = this.magazineService.getMagazineById(id);
            if (magazine != null) {
                publicationWork = PublicationWorkDetailsResponseModel.fromMagazine(magazine);
            }
        } else {
            publicationWork = PublicationWorkDetailsResponseModel.fromBook(book);
        }

        if (publicationWork == null) {
            throw new Exception("Publication work not found!");
        }

        publicationWork.setRating(this.ratingService.getAverageRatingOfPublicationWork(id));

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        publicationWork.setHasBeenRatedByIp(this.ratingService.publicationWorkHasBeenRatedByIp(id, ipAddress));

        return publicationWork;
    }

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
    public PublicationWorkModel update(@RequestBody PublicationWorkModel publicationWork) {
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

    @RequestMapping(value = "/publicationwork/filter")
    public FilteredPublicationWorksResponseModel filter(
            @RequestParam PublicationWorksFilterType type,
            @RequestParam String value,
            @RequestParam int page) throws Exception {
        FilteredPublicationWorksResponseModel result = new FilteredPublicationWorksResponseModel();

        Iterable<Book> books = null;
        Iterable<Magazine> magazines = null;

        switch (type) {
            case AUTHOR:
                Author author = this.authorService.getAuthorByName(value);
                if (author != null) {
                    books = this.bookService.getByAuthor(author, page, DEFAULT_PAGE_SIZE);
                    magazines = this.magazineService.getByAuthor(author, page, DEFAULT_PAGE_SIZE);
                }

                break;
            case GENRE:
                books = this.bookService.getByGenre(PublicationWorkGenre.valueOf(value), page, DEFAULT_PAGE_SIZE);
                magazines = this.magazineService.getByGenre(PublicationWorkGenre.valueOf(value), page, DEFAULT_PAGE_SIZE);
                break;
            default:
                throw new Exception("Invalid filter type!");
        }

        if (books != null) {
            List<PublicationWorkSimpleResponseModel> bookResponseModels = new ArrayList<>();
            for (Book book : books) {
                PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromBook(book);
                publicationWork.setRating(this.ratingService.getAverageRatingOfPublicationWork(book.getId()));
                bookResponseModels.add(publicationWork);
            }

            result.setBooks(bookResponseModels);
        }

        if (magazines != null) {
            List<PublicationWorkSimpleResponseModel> magazineResponseModels = new ArrayList<>();
            for (Magazine magazine : magazines) {
                PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromMagazine(magazine);
                publicationWork.setRating(this.ratingService.getAverageRatingOfPublicationWork(magazine.getId()));
                magazineResponseModels.add(publicationWork);
            }

            result.setMagazines(magazineResponseModels);
        }

        return result;
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
