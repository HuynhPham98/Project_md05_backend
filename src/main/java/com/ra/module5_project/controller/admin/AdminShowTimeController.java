package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.dto.showTime.request.ShowTimeRequest;
import com.ra.module5_project.model.dto.showTime.request.ShowTimeRequestUpdate;
import com.ra.module5_project.model.dto.showTime.response.ShowTimePagination;
import com.ra.module5_project.model.entity.ShowTime;
import com.ra.module5_project.service.showTime.ShowTimeService;
import jakarta.validation.Valid;
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
@RequestMapping("/api.myService.com/v1/admin/showTimes")
public class AdminShowTimeController {
    @Autowired
    private ShowTimeService showTimeService ;

    @GetMapping
    public ResponseEntity<ShowTimePagination> findAllAndSearch(@PageableDefault(page = 0 , size = 5 , sort = "id",direction = Sort.Direction.ASC)Pageable pageable,
                                                               @RequestParam(required = false)Long movieId,
                                                               @RequestParam(required = false) Long theaterId,
                                                               @RequestParam(required = false) Long screenRoomId ,
                                                               @RequestParam(required = false) Long showTimeId
                                                               ){
        return new ResponseEntity<>(showTimeService.findAllAndSearch(pageable,movieId,theaterId,screenRoomId,showTimeId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTime> findById(@PathVariable long id){
        return new ResponseEntity<>(showTimeService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createShowTime(@Valid @RequestBody ShowTimeRequest showTimeRequest){
        ShowTime showTime = showTimeService.save(showTimeRequest) ;
        if(showTime == null){
            return new ResponseEntity<>("Show time existed",HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(showTime,HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShowTime(@PathVariable long id
            , @Valid @RequestBody ShowTimeRequestUpdate showTimeRequestUpdate){
        ShowTime showTime = showTimeService.update(id,showTimeRequestUpdate);
        if(showTime == null){
            return new ResponseEntity<>("Show time existed",HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(showTime,HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        showTimeService.deleteById(id);
        return new ResponseEntity<>("Delete show time successfully",HttpStatus.OK);
    }
    @GetMapping("/getShowTimeByMovieAndDate")
    public ResponseEntity<List<ShowTime>> getShowTimeByMovieAndDate(
            @RequestParam Long movieId,
            @RequestParam LocalDate date,
            @RequestParam String theaterName
    ) {

        return new ResponseEntity<>(showTimeService.getShowTimeByMovieAndDate(movieId, date,theaterName), HttpStatus.OK);
    }

    @GetMapping("/getShowTimeByScreenRoom/{id}")
    public ResponseEntity<List<ShowTime>> getListByScreenRoomId(@PathVariable long id){
        return new ResponseEntity<>(showTimeService.getShowTimeByScreenRoom(id),HttpStatus.OK);
    }
}
