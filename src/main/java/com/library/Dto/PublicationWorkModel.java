package com.library.Dto;

import com.library.Model.PublicationWorkGenre;

import java.util.Date;

public class PublicationWorkModel {
    private long id;

    private String title;

    private String authors;

    private int pages;

    private Date publicationDate;

    private PublicationWorkGenre genre;

    private PublicationWorkType type;

    private int issue;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public PublicationWorkGenre getGenre() {
        return genre;
    }

    public void setGenre(PublicationWorkGenre genre) {
        this.genre = genre;
    }

    public PublicationWorkType getType() {
        return type;
    }

    public void setType(PublicationWorkType type) {
        this.type = type;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
