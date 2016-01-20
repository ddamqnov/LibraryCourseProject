package com.library.Model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public abstract class PublicationWork {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Length(min = 3, max = 200)
    private String title;

    @ManyToMany
    private Set<Author> authors;

    @NotNull
    @Min(1)
    private int pages;

    @NotNull
    private Date publicationDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Rating> ratings;

    @NotNull
    private PublicationWorkGenre genre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRating(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
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
}
