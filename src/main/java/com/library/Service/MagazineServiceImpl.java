package com.library.Service;

import com.library.Model.Author;
import com.library.Model.Magazine;
import com.library.Model.PublicationWorkGenre;
import com.library.Repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Iterable<Magazine> getPage(int page, int pageSize) {
        Pageable pageable = new PageRequest(0, pageSize, new Sort(Sort.Direction.DESC, "id"));
        return magazineRepository.findAll(pageable).getContent();
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

    @Override
    public Iterable<Magazine> getByAuthor(Author author, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.magazineRepository.getByAuthor(author, pageable);
    }

    @Override
    public Iterable<Magazine> getByGenre(PublicationWorkGenre genre, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.magazineRepository.getByGenre(genre, pageable);
    }
}
