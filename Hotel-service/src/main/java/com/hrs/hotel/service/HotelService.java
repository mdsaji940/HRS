package com.hrs.hotel.service;

import com.hrs.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String id);
}
