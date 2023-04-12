package com.hrs.rating.service.repositories;

import com.hrs.rating.service.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepositories extends JpaRepository<Rating, String> {

    // custom finder methods
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
