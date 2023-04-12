package com.hrs.rating.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_ratings")
public class Rating {

    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private String rating;
    private String feedback;

}
