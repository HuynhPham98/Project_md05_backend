package com.ra.module5_project.controller.user;

import com.ra.module5_project.model.dto.GiftUser.request.GiftUserRequest;
import com.ra.module5_project.model.entity.GiftUser;
import com.ra.module5_project.security.principle.UserPrinciple;
import com.ra.module5_project.service.giftUser.GiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/user/userGifts")
public class UserGiftController {
    @Autowired
    private GiftUserService giftUserService ;

    @GetMapping("/checkReceiveGift")
    public ResponseEntity<Boolean> checkUserReceiveGift(@AuthenticationPrincipal UserPrinciple userPrinciple){
        return new ResponseEntity<>(giftUserService.checkReceiveGift(userPrinciple.getUser().getId()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GiftUser> saveGiftUser(@RequestBody GiftUserRequest giftUserRequest){
        return new ResponseEntity<>(giftUserService.save(giftUserRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftUser> updateStatusGiftUser(@PathVariable long id){
        return new ResponseEntity<>(giftUserService.useGift(id),HttpStatus.OK);
    }

    @GetMapping("/getGiftUser")
    public ResponseEntity<List<GiftUser>> getGiftUser(@AuthenticationPrincipal UserPrinciple userPrinciple){
        return new ResponseEntity<>(giftUserService.getGiftUser(userPrinciple.getUser().getId()),HttpStatus.OK);
    }

    @GetMapping("/{giftUserId}")
    public ResponseEntity<GiftUser> findById(@PathVariable long giftUserId){
        return new ResponseEntity<>(giftUserService.findById(giftUserId),HttpStatus.OK);
    }
}
