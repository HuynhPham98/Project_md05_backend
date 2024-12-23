package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.dto.screenRoom.request.ScreenRoomRequest;
import com.ra.module5_project.model.dto.screenRoom.response.ScreenRoomPagination;
import com.ra.module5_project.model.dto.screenRoom.response.ScreenRoomResponse;
import com.ra.module5_project.model.entity.ScreenRoom;
import com.ra.module5_project.service.screenRoom.ScreenRoomService;
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
@RequestMapping("/api.myService.com/v1/admin/screenRooms")
public class AdminScreenRoomController {
    @Autowired
    private ScreenRoomService screenRoomService ;
    @GetMapping
    public ResponseEntity<ScreenRoomPagination> findAllAndSearch(@PageableDefault(page = 0 , size = 5 , sort = "id" , direction = Sort.Direction.ASC)Pageable pageable,
                                                                 @RequestParam(required = false) String search){
        return new ResponseEntity<>(screenRoomService.findAllAndSearch(pageable, search), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ScreenRoomRequest screenRoomRequest){
        ScreenRoomResponse screenRoomResponse = screenRoomService.save(screenRoomRequest) ;
        if(screenRoomResponse == null){
            return new ResponseEntity<>("Theater existed screen name or the theater is full",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(screenRoomResponse,HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id ,
                                    @Valid @RequestBody ScreenRoomRequest screenRoomRequest){
        ScreenRoomResponse screenRoomResponse = screenRoomService.update(id, screenRoomRequest);
        if(screenRoomResponse != null){
            return  new ResponseEntity<>(screenRoomResponse,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Theater existed screen name" , HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        screenRoomService.deleteById(id);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenRoomResponse> findById(@PathVariable long id){
        return new ResponseEntity<>(screenRoomService.findById(id),HttpStatus.OK);
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
