package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.entity.Seat;
import com.ra.module5_project.service.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/seats")
public class SeatController {

    @Autowired
    private SeatService seatService ;

    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<Seat>> getListByScreenId(@PathVariable long id){
        return new ResponseEntity<>(seatService.getSeatByScreenId(id), HttpStatus.OK);
    }

}
