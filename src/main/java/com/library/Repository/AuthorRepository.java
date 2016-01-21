package com.library.Repository;

import com.library.Model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query("SELECT author FROM Author as author WHERE author.name = (:name)")
    List<Author> getAuthorByName(@Param("name") String authorName);
}
