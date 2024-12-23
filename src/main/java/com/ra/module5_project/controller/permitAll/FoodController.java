package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.entity.ComboFood;
import com.ra.module5_project.service.comboFood.ComboFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/comboFoods")
public class FoodController {
    @Autowired
    private ComboFoodService comboFoodService ;

    @GetMapping
    public ResponseEntity<List<ComboFood>> userGetAll(){
        return new ResponseEntity<>(comboFoodService.userFindAll(), HttpStatus.OK);
    }
}
