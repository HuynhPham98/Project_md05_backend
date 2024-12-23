package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.dto.theater.response.TheaterPagination;
import com.ra.module5_project.model.entity.Theater;
import com.ra.module5_project.service.theater.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/theaters")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;
    @GetMapping
    public ResponseEntity<TheaterPagination> findAllAndSearchName(@PageableDefault(page = 0 , size = 5 ,sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
                                                                  @RequestParam(required = false) String search){
        return new ResponseEntity<>(theaterService.findAllAndSearch(pageable, search), HttpStatus.OK);
    }

    @GetMapping("/getTheaters")
    public ResponseEntity<List<Theater>> getTheaters() {
        return  new ResponseEntity<>(theaterService.getTheaters(),HttpStatus.OK);
    }

    @GetMapping("/getTheaterByCity")
    public ResponseEntity<List<Theater>> getTheaterByCity(@RequestParam String cityName){
        return new ResponseEntity<>(theaterService.getTheaterByCity(cityName),HttpStatus.OK);
    }
}
