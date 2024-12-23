package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.dto.Gift.request.GiftRequest;
import com.ra.module5_project.model.dto.Gift.response.GiftPagination;
import com.ra.module5_project.model.entity.Gift;
import com.ra.module5_project.service.giftService.GiftService;
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
@RequestMapping("/api.myService.com/v1/admin/gifts")
public class AdminGiftController {
    @Autowired
    private GiftService giftService ;

    @GetMapping("/getGiftEvent")
    public ResponseEntity<List<Gift>> getGiftCurrent() {
        return new ResponseEntity<>(giftService.getListCurrent(), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<GiftPagination> findAll(@PageableDefault(page = 0 , size = 5 , sort = "id" ,direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>(giftService.findAll(pageable),HttpStatus.OK);
    }

    @PutMapping("/{giftId}")
    public ResponseEntity<Gift> updateGift(@PathVariable long giftId , @Valid @RequestBody GiftRequest giftRequest){
        return new ResponseEntity<>(giftService.update(giftId,giftRequest),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Gift> createGift(@Valid @RequestBody GiftRequest giftRequest){
        return new ResponseEntity<>(giftService.save(giftRequest),HttpStatus.CREATED);
    }

    @PutMapping("/uploadImage")
    public ResponseEntity<Gift> uploadImage(@RequestBody Gift gift){
        return new ResponseEntity<>(giftService.uploadImage(gift),HttpStatus.OK);
    }

    @DeleteMapping("/{giftId}")
    public ResponseEntity<?> deleteGiftById(@PathVariable long giftId){
        giftService.deleteById(giftId);
        return new ResponseEntity<>("Delete gift successfully",HttpStatus.OK);
    }
}
