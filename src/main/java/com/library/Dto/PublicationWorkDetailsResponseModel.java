package com.library.Dto;

import com.library.Model.Book;
import com.library.Model.Magazine;
import com.library.Model.PublicationWork;

import java.util.Date;

public class PublicationWorkDetailsResponseModel extends PublicationWorkSimpleResponseModel {
    private Date publicationDate;

    private int pages;

    private boolean hasBeenRatedByIp;

    public Date getPublicationDate() {
        return publicationDate;
    }

    public int getPages() {
        return pages;
    }

    public boolean isHasBeenRatedByIp() {
        return hasBeenRatedByIp;
    }

    public void setHasBeenRatedByIp(boolean hasBeenRatedByIp) {
        this.hasBeenRatedByIp = hasBeenRatedByIp;
    }

    public static PublicationWorkDetailsResponseModel fromBook(Book book) {
        PublicationWorkDetailsResponseModel publicationWork = fromPublicationWorkEntityModel(book);

        publicationWork.type = PublicationWorkType.BOOK;

        return publicationWork;
    }

    public static PublicationWorkDetailsResponseModel fromMagazine(Magazine magazine) {
        PublicationWorkDetailsResponseModel publicationWork = fromPublicationWorkEntityModel(magazine);

        publicationWork.type = PublicationWorkType.MAGAZINE;
        publicationWork.issue = magazine.getIssue();

        return publicationWork;
    }

    private static PublicationWorkDetailsResponseModel fromPublicationWorkEntityModel(PublicationWork pubWork) {
        PublicationWorkDetailsResponseModel publicationWork = new PublicationWorkDetailsResponseModel();

        publicationWork.title = pubWork.getTitle();
        publicationWork.id = pubWork.getId();
        publicationWork.genre = pubWork.getGenre();
        publicationWork.pages = pubWork.getPages();
        publicationWork.publicationDate = pubWork.getPublicationDate();
        publicationWork.authors = getAuthorsMerged(pubWork.getAuthors());

        return publicationWork;
    }
}
