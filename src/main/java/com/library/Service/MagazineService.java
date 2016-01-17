package com.library.Service;

import com.library.Model.Magazine;

/**
 * Created by D on 16.1.2016 г..
 */
public interface MagazineService {
    Iterable<Magazine> getAll();

    Magazine save(Magazine magazine);

    Magazine getMagazineById(Long id);

    void deleteMagazine(Long id);

    Magazine updateMagazine(Magazine magazine);
}


