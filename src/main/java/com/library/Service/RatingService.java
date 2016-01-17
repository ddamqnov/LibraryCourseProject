package com.library.Service;

import com.library.Model.Rating;

/**
 * Created by D on 16.1.2016 г..
 */
public interface RatingService {
    void save(Rating rating);

    void update(double vote);
}
