package com.library.Service;

import com.library.Model.Rating;
import com.library.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public double getAverageRatingOfPublicationWork(long id) {
        List<Double> result = ratingRepository.getAverageRatingOfPublicationWork(id);

        if (result.isEmpty() || result.get(0) == null) {
            return 0;
        }

        return result.get(0);
    }
}
