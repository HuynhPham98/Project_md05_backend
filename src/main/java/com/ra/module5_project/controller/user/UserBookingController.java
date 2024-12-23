package com.ra.module5_project.controller.user;

import com.ra.module5_project.model.dto.booking.request.BookingRequest;
import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.security.principle.UserPrinciple;
import com.ra.module5_project.service.booking.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/users/bookings")
public class UserBookingController {
    @Autowired
    private BookingService bookingService ;

    @GetMapping
    public ResponseEntity<List<Booking>> findAllByUserId(@AuthenticationPrincipal UserPrinciple userPrinciple){
        return new ResponseEntity<>(bookingService.findAllByUserId(userPrinciple.getUser().getId()),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Booking> createBooking (@Valid @RequestBody BookingRequest bookingRequest,
                                                  @AuthenticationPrincipal UserPrinciple userPrinciple){
        return new ResponseEntity<>(bookingService.save(bookingRequest,userPrinciple), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable long id){
        return new ResponseEntity<>(bookingService.findById(id),HttpStatus.OK);
    }
}
