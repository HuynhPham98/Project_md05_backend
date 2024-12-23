package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.dto.city.request.CityRequest;
import com.ra.module5_project.model.dto.city.request.CityRequestUpdate;
import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.service.city.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/city")
public class AdminCityController {
    @Autowired
    private CityService cityService;
//    @GetMapping
//    public ResponseEntity<CityPagination> findAllAndSearchName(@PageableDefault(page = 0 , size = 5 , sort = "id" , direction = Sort.Direction.ASC) Pageable pageable ,
//                                                               @RequestParam(required = false) String search){
//        return new ResponseEntity<>(cityService.findAll(pageable,search), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        return new ResponseEntity<>(cityService.getAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCity( @Valid @RequestBody CityRequest cityRequest){
        City city = cityService.addCity((cityRequest));
        if(city != null){
            return new ResponseEntity<>(city ,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("create city error" , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCity(@PathVariable long id , @Valid @RequestBody CityRequestUpdate cityRequestUpdate){
        City city = cityService.updateCity(id, cityRequestUpdate);
        if(city != null){
            return  new ResponseEntity<>(city,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("city exist",HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable long id){
        cityService.deleteById(id);
        return new ResponseEntity<>("Delete success",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable long id){
        return new ResponseEntity<>(cityService.findById(id),HttpStatus.OK);
    }
}
