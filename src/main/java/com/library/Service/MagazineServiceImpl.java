package com.library.Service;

import com.library.Model.Magazine;
import com.library.Repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by D on 16.1.2016 г..
 */
@Service
@Transactional
public class MagazineServiceImpl implements MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    @Override
    public Iterable<Magazine> getAll() {
        return magazineRepository.findAll();
    }

    @Override
    public Magazine save(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    @Override
    public Magazine getMagazineById(Long id) {
        return magazineRepository.findOne(id);
    }

    @Override
    public void deleteMagazine(Long id) {
        magazineRepository.delete(id);
    }

    @Override
    public Magazine updateMagazine(Magazine magazine) {
        //update attributes
        return null;
    }
}
