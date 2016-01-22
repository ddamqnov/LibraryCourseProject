package com.library.Controller;

import com.library.Dto.RatingRequestModel;
import com.library.Model.PublicationWork;
import com.library.Model.Rating;
import com.library.Service.BookService;
import com.library.Service.MagazineService;
import com.library.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MagazineService magazineService;

    @RequestMapping(value = "/rating", method = RequestMethod.POST)
    private double ratePublicationWork(@RequestBody RatingRequestModel rating, HttpServletRequest request) throws Exception {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        boolean publicationWorkHasBeenRatedByIp =
            this.ratingService.publicationWorkHasBeenRatedByIp(rating.getPublicationWorkId(), ipAddress);
        if (publicationWorkHasBeenRatedByIp) {
            throw new Exception("The publication work has already been rated by you!");
        }

        long publicationWorkId = rating.getPublicationWorkId();
        PublicationWork publicationWork = this.bookService.getBookById(publicationWorkId);
        if (publicationWork == null) {
            publicationWork = this.magazineService.getMagazineById(publicationWorkId);
        }

        if (publicationWork == null) {
            throw new Exception("Publication work not found!");
        }

        Rating newRating = new Rating();
        newRating.setPublicationWork(publicationWork);
        newRating.setValue(rating.getValue());
        newRating.setIp(ipAddress);

        this.ratingService.save(newRating);

        return this.ratingService.getAverageRatingOfPublicationWork(publicationWorkId);
    }
}
