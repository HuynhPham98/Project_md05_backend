package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.dto.seat.request.SeatRequest;
import com.ra.module5_project.model.dto.seat.response.SeatPagination;
import com.ra.module5_project.model.dto.seat.response.SeatResponse;
import com.ra.module5_project.model.entity.Seat;
import com.ra.module5_project.service.seat.SeatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/admin/seats")
public class AdminSeatController {
    @Autowired
    private SeatService seatService ;
    @GetMapping
    public ResponseEntity<SeatPagination> findAllAndSearch(@PageableDefault(page = 0 , size = 5 , sort = "id" , direction = Sort.Direction.ASC)Pageable pageable,
                                                           @RequestParam(required = false) String search){
        return new ResponseEntity<>(seatService.findAllAndSearch(pageable, search), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> findById(@PathVariable long id){
        return new ResponseEntity<>(seatService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSeat(@Valid @RequestBody SeatRequest seatRequest){
        SeatResponse seatResponse = seatService.save(seatRequest);
        if(seatResponse == null){
            return new ResponseEntity<>("Seat name exist in screen room", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(seatResponse,HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeat(@PathVariable long id ,
                                        @Valid @RequestBody SeatRequest seatRequest){
        SeatResponse seatResponse = seatService.update(id,seatRequest);
        if(seatResponse == null){
            return new ResponseEntity<>("Seat name exist in screen room", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(seatResponse,HttpStatus.OK);
        }
    }

    @PutMapping("/updateAll")
    public ResponseEntity<?> updateAll(
                                        @Valid @RequestBody List<Seat> seats){
         seatService.updateAll(seats);
            return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        seatService.deleteById(id);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<Seat>> getListByScreenId(@PathVariable long id){
        return new ResponseEntity<>(seatService.getSeatByScreenId(id),HttpStatus.OK);
    }
}
