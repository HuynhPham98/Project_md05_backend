package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.entity.BookingSeat;
import com.ra.module5_project.model.entity.Seat;
import com.ra.module5_project.service.bookingSeat.BookingSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/bookingSeats")
public class BookingSeatController {
    @Autowired
    private BookingSeatService bookingSeatService ;
    @GetMapping("/{bookingId}")
    public ResponseEntity<List<BookingSeat>> getBookingSeatById(@PathVariable long bookingId){
        return new ResponseEntity<>(bookingSeatService.findAllByBookingId(bookingId), HttpStatus.OK);
    }

    @GetMapping("/getBookingSeatByShowTime/{showTimeId}")
    public ResponseEntity<List<Seat>> getSeatByShowTime(@PathVariable long showTimeId){
        return  new ResponseEntity<>(bookingSeatService.getListSeatSold(showTimeId),HttpStatus.OK);
    }
}
