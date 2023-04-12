package com.hrs.user.service.service.imp;

import com.hrs.user.service.Exception.ResourceNotFoundException;
import com.hrs.user.service.entities.Hotel;
import com.hrs.user.service.entities.Rating;
import com.hrs.user.service.entities.User;
import com.hrs.user.service.external.services.HotelService;
import com.hrs.user.service.repositories.UserRepository;
import com.hrs.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        // implements rating service
//        restTemplate.getForObject()
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user with given id is not found with id:" + userId));

        //fetch rating of the above user from rating service
        // http://localhost:8083/ratings/users/ac72c500-9235-4f36-b9cd-72ab166c8b82

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
//      http://localhost:8082/hotels/2c3968b0-4a8a-4e5f-83df-1d9f59329c74

        List<Rating> ratingList = ratings.stream().map(rating -> {

//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            logger.info("response status code", forEntity.getStatusCode());

            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
