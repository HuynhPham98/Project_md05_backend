package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.dto.booking.request.BookingRequest;
import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.security.principle.UserPrinciple;
import com.ra.module5_project.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService ;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@AuthenticationPrincipal UserPrinciple userPrinciple ,
                                                 @RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.save(bookingRequest,userPrinciple), HttpStatus.CREATED);

    }

    @GetMapping("/getMoneyBySeat")
    public ResponseEntity<Double> getMoneyBySeat(@RequestParam Long showTimeId , @RequestParam List<Long> seatIds){
        return new ResponseEntity<>(bookingService.convertSeatToMoney(showTimeId, seatIds),HttpStatus.OK);
    }

    @GetMapping("/getPriceOfSeat/seatId/{seatId}/showTimeId/{showTimeId}")
    public ResponseEntity<Double> getPriceOfSeat(@PathVariable long seatId , @PathVariable long showTimeId){
        return new ResponseEntity<>(bookingService.seatToMoney(showTimeId,seatId),HttpStatus.OK);
    }
}
