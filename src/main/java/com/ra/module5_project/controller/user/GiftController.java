package com.ra.module5_project.controller.user;

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
@RequestMapping("/api.myService.com/v1/user/gifts")
public class GiftController {
    @Autowired
    private GiftService giftService ;

    @GetMapping("/getGiftEvent")
    public ResponseEntity<List<Gift>> getGiftCurrent() {
        return new ResponseEntity<>(giftService.getListCurrent(), HttpStatus.OK);
    }







}
