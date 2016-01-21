package com.library.Service;

import com.library.Model.Book;
import com.library.Model.Magazine;
import org.springframework.data.domain.Pageable;

public interface MagazineService {
    Iterable<Magazine> getAll();

    Iterable<Magazine> getPage(Pageable pageable);

    Magazine save(Magazine magazine);

    Magazine getMagazineById(Long id);

    void deleteMagazine(Long id);

    Magazine updateMagazine(Magazine magazine);
}


