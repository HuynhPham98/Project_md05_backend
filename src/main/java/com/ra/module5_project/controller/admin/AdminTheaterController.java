package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.dto.theater.request.TheaterRequest;
import com.ra.module5_project.model.dto.theater.request.TheaterRequestUpdate;
import com.ra.module5_project.model.dto.theater.response.TheaterPagination;
import com.ra.module5_project.model.entity.Theater;
import com.ra.module5_project.service.theater.TheaterService;
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
@RequestMapping("/api.myService.com/v1/admin/theaters")
public class AdminTheaterController {
    @Autowired
    private TheaterService theaterService;
    @GetMapping
    public ResponseEntity<TheaterPagination> findAllAndSearchName(@PageableDefault(page = 0 , size = 5 ,sort = "id",direction = Sort.Direction.ASC)Pageable pageable,
                                                                  @RequestParam(required = false) String search){
        return new ResponseEntity<>(theaterService.findAllAndSearch(pageable, search), HttpStatus.OK);
    }

    @GetMapping("/getTheaters")
    public ResponseEntity<List<Theater>> getTheaters() {
        return  new ResponseEntity<>(theaterService.getTheaters(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> findById(@PathVariable long id){
        return new ResponseEntity<>(theaterService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/checkNameExist/{name}")
    public ResponseEntity<Boolean> checkNameExist(@PathVariable String name){
        return new ResponseEntity<>(theaterService.checkNameExist(name),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Theater> createTheater(@Valid @RequestBody TheaterRequest theaterRequest){
        return new ResponseEntity<>(theaterService.save(theaterRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheater(@PathVariable long id,
                                           @Valid @RequestBody TheaterRequestUpdate theaterRequestUpdate){
        Theater theater = theaterService.update(id,theaterRequestUpdate);
        if(theater == null){
            return new ResponseEntity<>("Theater name existed",HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(theater,HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        theaterService.deleteById(id);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }

    @GetMapping("/getTheaterByCity")
    public ResponseEntity<List<Theater>> getTheaterByCity(@RequestParam String cityName){
        return new ResponseEntity<>(theaterService.getTheaterByCity(cityName),HttpStatus.OK);
    }

}
