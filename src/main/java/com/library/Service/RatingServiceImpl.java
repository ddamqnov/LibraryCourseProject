package com.library.Service;

import com.library.Model.Rating;
import com.library.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by D on 16.1.2016 г..
 */
@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public void update(double vote) {

    }
}