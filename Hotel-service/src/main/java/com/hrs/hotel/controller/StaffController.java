package com.hrs.hotel.controller;

import org.bouncycastle.pqc.crypto.rainbow.RainbowSigner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getStaffs(){
        List<String> list = Arrays.asList("Ram", "Sita", "Shyam", "Jhagan");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
