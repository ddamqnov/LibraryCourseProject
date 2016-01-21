package com.library.Repository;

import com.library.Model.Magazine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends PagingAndSortingRepository<Magazine, Long> {
}
