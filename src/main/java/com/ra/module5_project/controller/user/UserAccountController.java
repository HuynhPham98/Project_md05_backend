package com.ra.module5_project.controller.user;

import com.ra.module5_project.model.dto.user.request.UserUpdateDto;
import com.ra.module5_project.model.dto.user.request.UserUpdatePasswordDto;
import com.ra.module5_project.model.dto.user.response.UserResponse;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.security.principle.UserPrinciple;
import com.ra.module5_project.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myService.com/v1/user/account")
public class UserAccountController {
    @Autowired
    private UserService userService ;

    @GetMapping
    public ResponseEntity<UserResponse> getInformation(@AuthenticationPrincipal UserPrinciple userPrinciple){
        UserResponse userResponse = userService.userToUserResponse(userPrinciple.getUser());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @ModelAttribute UserUpdateDto userUpdateDto,
                                                   @AuthenticationPrincipal UserPrinciple userPrinciple){
        UserResponse userResponse = userService.updateUser(userPrinciple.getUser(),userUpdateDto);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UserUpdatePasswordDto userUpdatePasswordDto,
                                            @AuthenticationPrincipal UserPrinciple userPrinciple){
        boolean rs = userService.changePassword(userPrinciple.getUser(),userUpdatePasswordDto);
        if(rs){
            return new ResponseEntity<>("change password success",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("old password wrong or new password not match ",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserPrinciple userPrinciple){
        User user = userPrinciple.getUser();
        user.setStatusLogin(false);
        userService.logout(user);
           return new ResponseEntity<>("logout success",HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal UserPrinciple userPrinciple){
        return new ResponseEntity<>(userPrinciple.getUser(),HttpStatus.OK);
    }

    @PutMapping("/updateGetCoin")
    public ResponseEntity<?> updateGetCoin(@AuthenticationPrincipal UserPrinciple userPrinciple){
        return new ResponseEntity<>(userService.updateGetCoin(userPrinciple.getUser()),HttpStatus.OK);
    }

    @PutMapping("/useCoin")
    public ResponseEntity<?> useCoin(@AuthenticationPrincipal UserPrinciple userPrinciple){
        userService.useCoin(userPrinciple.getUser());
        return new ResponseEntity<>("update coin successfully",HttpStatus.OK);
    }
}
