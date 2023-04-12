package com.hrs.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    private String id;
    private String name;
    private String location;
    private String about;
}
