package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.dto.user.request.UserLoginRequest;
import com.ra.module5_project.model.dto.user.request.UserRegisterRequest;
import com.ra.module5_project.model.dto.user.response.UserLoginResponse;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.repository.UserRepository;
import com.ra.module5_project.security.jwt.JWTProvider;
import com.ra.module5_project.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myService.com/v1/auth")
public class AuthController {
    @Autowired
    private final AuthService authService ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final JWTProvider jwtProvider ;
    public AuthController(AuthService authService, JWTProvider jwtProvider) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }
//Đk
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
           boolean rs = authService.register(userRegisterRequest);
           if(rs){
               return new ResponseEntity<>("register success", HttpStatus.CREATED);
           }else {
               return new ResponseEntity<>("register error", HttpStatus.BAD_REQUEST);
           }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserLoginResponse> signIn(@Valid @RequestBody UserLoginRequest userLoginRequest){
        return new ResponseEntity<>(authService.login(userLoginRequest),HttpStatus.OK);
    }

    @GetMapping("/active-account/{email}/verify/{code}")
    public ResponseEntity<?> activeAccount(@PathVariable String email ,@PathVariable long code){
        User user = userRepository.getUserByEmail(email);
        if(user != null){
            if(user.getActiveCode() == code){
                user.setStatus(true);
                userRepository.save(user);
                return new ResponseEntity<>("Active account success",HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Active account error",HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>("Active account error",HttpStatus.BAD_REQUEST);
        }
    }
}
