package com.ra.module5_project.controller.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.module5_project.model.dto.booking.request.BookingRequest;
import com.ra.module5_project.model.dto.booking.response.BookingPagination;
import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.security.principle.UserPrinciple;
import com.ra.module5_project.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/admin/bookings")
public class AdminBookingController {
    @Autowired
    private BookingService bookingService ;
    @GetMapping
    public ResponseEntity<BookingPagination> findAll(@PageableDefault(page = 0 , size = 5 , sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                     @JsonFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) LocalDate date ,
                                                     @RequestParam(required = false)Long movieId,
                                                     @RequestParam(required = false) Long theaterId,
                                                     @RequestParam(required = false) Long screenRoomId ,
                                                     @RequestParam(required = false) Long showTimeId
    ){
        return new ResponseEntity<>(bookingService.findAll(pageable,date,movieId,theaterId,screenRoomId,showTimeId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable long id){
        return new ResponseEntity<>(bookingService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@AuthenticationPrincipal UserPrinciple userPrinciple ,
                                                 @RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.save(bookingRequest,userPrinciple),HttpStatus.CREATED);

    }

    @GetMapping("/getMoneyBySeat")
    public ResponseEntity<Double> getMoneyBySeat(@RequestParam Long showTimeId , @RequestParam List<Long> seatIds){
        return new ResponseEntity<>(bookingService.convertSeatToMoney(showTimeId, seatIds),HttpStatus.OK);
    }
}
