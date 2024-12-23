package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.dto.screenRoom.response.ScreenRoomPagination;
import com.ra.module5_project.model.entity.ScreenRoom;
import com.ra.module5_project.service.screenRoom.ScreenRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/screenRooms")
public class ScreenRoomController {
    @Autowired
    private ScreenRoomService screenRoomService ;

    @GetMapping
    public ResponseEntity<ScreenRoomPagination> findAllAndSearch(@PageableDefault(page = 0 , size = 5 , sort = "id" , direction = Sort.Direction.ASC) Pageable pageable,
                                                                 @RequestParam(required = false) String search){
        return new ResponseEntity<>(screenRoomService.findAllAndSearch(pageable, search), HttpStatus.OK);
    }

    @GetMapping("/getScreenByTheater/{id}")
    public ResponseEntity<List<ScreenRoom>> getScreenByTheater(@PathVariable long id){
        return new ResponseEntity<>(screenRoomService.getScreenByTheater(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ScreenRoom>> getAll(){
        return  new ResponseEntity<>(screenRoomService.getAll(),HttpStatus.OK);
    }

}
