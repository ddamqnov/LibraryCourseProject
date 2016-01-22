package com.library.Service;

import com.library.Model.Author;
import com.library.Model.Magazine;
import com.library.Model.PublicationWorkGenre;

public interface MagazineService {
    Iterable<Magazine> getAll();

    Iterable<Magazine> getPage(int page, int pageSize);

    Magazine save(Magazine magazine);

    Magazine getMagazineById(Long id);

    void deleteMagazine(Long id);

    Magazine updateMagazine(Magazine magazine);

    Iterable<Magazine> getByAuthor(Author author, int page, int pageSize);

    Iterable<Magazine> getByGenre(PublicationWorkGenre genre, int page, int pageSize);
}


