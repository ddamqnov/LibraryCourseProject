package com.library.Repository;

import com.library.Model.Author;
import com.library.Model.Magazine;
import com.library.Model.PublicationWorkGenre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagazineRepository extends PagingAndSortingRepository<Magazine, Long> {
    @Query("SELECT magazine FROM Magazine magazine WHERE (:author) MEMBER OF magazine.authors")
    List<Magazine> getByAuthor(@Param("author") Author author, Pageable pageable);

    @Query("SELECT magazine FROM Magazine magazine WHERE magazine.genre = (:genre)")
    List<Magazine> getByGenre(@Param("genre") PublicationWorkGenre genre, Pageable pageable);
}
