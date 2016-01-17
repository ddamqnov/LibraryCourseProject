package com.library.Repository;

import com.library.Model.PublicationWork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by D on 16.1.2016 г..
 */
@Repository
public interface PublicationWorkRepository extends CrudRepository<PublicationWork, Long> {
}
