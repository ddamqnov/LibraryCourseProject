package com.library.Service;

import com.library.Model.Rating;


public interface RatingService {
    void save(Rating rating);

    Double getAverageRatingOfPublicationWork(long publicationWorkId);

    boolean publicationWorkHasBeenRatedByIp(long publicationWorkId, String ip);
}
