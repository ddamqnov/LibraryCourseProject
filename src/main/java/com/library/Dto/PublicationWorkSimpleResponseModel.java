package com.library.Dto;

import com.library.Model.Author;
import com.library.Model.Book;
import com.library.Model.Magazine;
import com.library.Model.PublicationWork;

public class PublicationWorkSimpleResponseModel {
    protected long id;

    protected String title;

    protected String authors;

    protected Double rating;

    protected int issue;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getIssue() {
        return issue;
    }

    public static PublicationWorkSimpleResponseModel fromBook(Book book) {
        return fromPublicationWorkEntityModel(book);
    }

    public static PublicationWorkSimpleResponseModel fromMagazine(Magazine magazine) {
        PublicationWorkSimpleResponseModel publicationWork = fromPublicationWorkEntityModel(magazine);

        publicationWork.issue = magazine.getIssue();

        return publicationWork;
    }

    protected static String getAuthorsMerged(Iterable<Author> authors) {
        StringBuilder result = new StringBuilder();

        for (Author author: authors) {
            result.append(author.getName());
            result.append(", ");
        }

        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }

    private static PublicationWorkSimpleResponseModel fromPublicationWorkEntityModel(PublicationWork pubWork) {
        PublicationWorkSimpleResponseModel publicationWork = new PublicationWorkSimpleResponseModel();

        publicationWork.title = pubWork.getTitle();
        publicationWork.id = pubWork.getId();
        publicationWork.authors = getAuthorsMerged(pubWork.getAuthors());

        return publicationWork;
    }
}
