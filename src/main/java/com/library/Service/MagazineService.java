package com.library.Service;

import com.library.Model.Magazine;

public interface MagazineService {
    Iterable<Magazine> getAll();

    Magazine save(Magazine magazine);

    Magazine getMagazineById(Long id);

    void deleteMagazine(Long id);

    Magazine updateMagazine(Magazine magazine);
}


