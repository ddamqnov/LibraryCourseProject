package com.library.Repository;

import com.library.Model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by D on 16.1.2016 г..
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
