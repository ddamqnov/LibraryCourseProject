package com.library.Service;

import com.library.Model.Rating;

public interface RatingService {
    void save(Rating rating);

    void update(double vote);
}
