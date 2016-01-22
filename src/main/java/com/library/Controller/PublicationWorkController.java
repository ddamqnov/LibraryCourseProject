package com.library.Controller;

import com.library.Dto.PublicationWorkDetailsResponseModel;
import com.library.Dto.PublicationWorkModel;
import com.library.Dto.PublicationWorkSimpleResponseModel;
import com.library.Dto.PublicationWorkType;
import com.library.Model.Author;
import com.library.Model.Book;
import com.library.Model.Magazine;
import com.library.Service.AuthorService;
import com.library.Service.BookService;
import com.library.Service.MagazineService;
import com.library.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PublicationWorkController {
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
        Pageable pageable = new PageRequest(0, count, new Sort(Sort.Direction.DESC, "id"));
        Iterable<Book> books = bookService.getPage(pageable);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Book book: books) {
            PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromBook(book);
            publicationWork.setRating(this.ratingService.getAverageRatingOfPublicationWork(book.getId()));
            result.add(publicationWork);
        }

        return result;
    }

    @RequestMapping(value = "/magazine/last", method = RequestMethod.GET)
    public Iterable<PublicationWorkSimpleResponseModel> getLastMagazines(@RequestParam int count) {
        Pageable pageable = new PageRequest(0, count, new Sort(Sort.Direction.DESC, "id"));
        Iterable<Magazine> magazines = magazineService.getPage(pageable);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Magazine magazine: magazines) {
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
