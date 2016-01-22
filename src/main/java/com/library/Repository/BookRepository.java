package com.library.Repository;

import com.library.Model.Author;
import com.library.Model.Book;
import com.library.Model.PublicationWorkGenre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    @Query("SELECT book FROM Book book WHERE (:author) MEMBER OF book.authors")
    List<Book> getByAuthor(@Param("author") Author author, Pageable pageable);

    @Query("SELECT book FROM Book book WHERE book.genre = (:genre)")
    List<Book> getByGenre(@Param("genre") PublicationWorkGenre genre, Pageable pageable);
}
