package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.entity.FoodBooking;
import com.ra.module5_project.service.foodBooking.FoodBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/admin/foodBookings")
public class AdminFoodBookingController {
    @Autowired
    private FoodBookingService foodBookingService ;

    @GetMapping("/getFoodByBookingId/{id}")
    public ResponseEntity<List<FoodBooking>> getFoodByBookingId(@PathVariable long id){
        return new ResponseEntity<>(foodBookingService.getFoodByBookingId(id), HttpStatus.OK);
    }
}
