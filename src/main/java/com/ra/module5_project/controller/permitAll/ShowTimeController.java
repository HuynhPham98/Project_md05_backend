package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.dto.showTime.response.ShowTimePagination;
import com.ra.module5_project.model.entity.ShowTime;
import com.ra.module5_project.service.showTime.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/showTimes")
public class ShowTimeController {
    @Autowired
    private ShowTimeService showTimeService ;

    @GetMapping
    public ResponseEntity<ShowTimePagination> findAllAndSearch(@PageableDefault(page = 0 , size = 5 , sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
                                                               @RequestParam(required = false)Long movieId,
                                                               @RequestParam(required = false) Long theaterId,
                                                               @RequestParam(required = false) Long screenRoomId ,
                                                               @RequestParam(required = false) Long showTimeId
    ){
        return new ResponseEntity<>(showTimeService.findAllAndSearch(pageable,movieId,theaterId,screenRoomId,showTimeId), HttpStatus.OK);
    }

    @GetMapping("/getShowTimeByScreenRoom/{id}")
    public ResponseEntity<List<ShowTime>> getListByScreenRoomId(@PathVariable long id){
        return new ResponseEntity<>(showTimeService.getShowTimeByScreenRoom(id),HttpStatus.OK);
    }

    @GetMapping("/getShowTimeByMovieAndDate")
    public ResponseEntity<List<ShowTime>> getShowTimeByMovieAndDate(
            @RequestParam Long movieId,
            @RequestParam LocalDate date,
            @RequestParam String theaterName
    ) {

        return new ResponseEntity<>(showTimeService.getShowTimeByMovieAndDate(movieId, date,theaterName), HttpStatus.OK);
    }
}
