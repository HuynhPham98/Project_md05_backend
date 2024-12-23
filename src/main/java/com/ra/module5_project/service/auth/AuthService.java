package com.ra.module5_project.service.auth;


import com.ra.module5_project.model.dto.user.request.UserLoginRequest;
import com.ra.module5_project.model.dto.user.request.UserRegisterRequest;
import com.ra.module5_project.model.dto.user.request.UserUpdatePasswordDto;
import com.ra.module5_project.model.dto.user.response.UserLoginResponse;
import com.ra.module5_project.model.entity.User;

public interface AuthService {
    UserLoginResponse login(UserLoginRequest userLoginRequest);
     User changePassword(UserUpdatePasswordDto userUpdatePasswordDto) ;
     boolean register(UserRegisterRequest userRegisterRequest);

}

