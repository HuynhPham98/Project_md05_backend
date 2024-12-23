package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.dto.comboFoot.request.ComboFoodRequest;
import com.ra.module5_project.model.dto.comboFoot.request.ComboFoodUpdate;
import com.ra.module5_project.model.dto.comboFoot.response.ComboFoodPagination;
import com.ra.module5_project.model.entity.ComboFood;
import com.ra.module5_project.service.comboFood.ComboFoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myService.com/v1/admin/comboFoods")
public class AdminComboFoodController {
    @Autowired
    private ComboFoodService comboFoodService ;

    @GetMapping
    public ResponseEntity<ComboFoodPagination> findAll(@PageableDefault(page = 0 , size = 5 , sort = "id" , direction = Sort.Direction.ASC)Pageable pageable){
        return new ResponseEntity<>(comboFoodService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComboFood> save(@Valid @RequestBody ComboFoodRequest comboFoodRequest){
        return new ResponseEntity<>(comboFoodService.save(comboFoodRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id , @Valid @RequestBody ComboFoodUpdate comboFoodUpdate){
        ComboFood comboFood = comboFoodService.update(id, comboFoodUpdate);
        if(comboFood == null){
            return new ResponseEntity<>("Combo food name existed" , HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(comboFood,HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        comboFoodService.deleteById(id);
        return new ResponseEntity<>("Delete combo food successfully",HttpStatus.OK);
    }

    @PutMapping("/updateImage")
    public ResponseEntity<ComboFood> updateImage( @RequestBody ComboFood comboFood){
        return new ResponseEntity<>(comboFoodService.updateImage(comboFood),HttpStatus.CREATED);
    }
}
