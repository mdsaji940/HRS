package com.hrs.rating.service.service;

import com.hrs.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    // create rating
    Rating createRating(Rating rating);

    // get all ratings
    List<Rating> getAllRatings();

    // get all ratings by userId
    List<Rating> getRatingsByUserId(String userId);

    // get all ratings by hotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
