package com.hrs.rating.service.service.impl;

import com.hrs.rating.service.entities.Rating;
import com.hrs.rating.service.repositories.RatingRepositories;
import com.hrs.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepositories ratingRepositories;

    @Override
    public Rating createRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepositories.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepositories.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepositories.findByUserId(userId);

    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepositories.findByHotelId(hotelId);
    }
}
