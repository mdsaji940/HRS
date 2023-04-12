package com.hrs.user.service.Controller;

import com.hrs.user.service.entities.User;
import com.hrs.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    int retryCount = 1;

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    // single user
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name= "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name= "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        log.info("Get Single User Handler: UserController");

        log.info("Retry count: {}", retryCount);
        retryCount ++;

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    // creating fallback method for circuitBreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//        log.info("Fallback is executed because service is down: ", ex.getMessage());

//        User user = User.user.getemail("dummy@gmail.com")
//                .name("Dummy").about("This user is created dummy because some services down")
//                .userId("1234")
//                .build();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

}
