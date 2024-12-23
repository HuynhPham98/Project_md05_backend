package com.ra.module5_project.service.auth;

import com.ra.module5_project.model.dto.user.request.UserLoginRequest;
import com.ra.module5_project.model.dto.user.request.UserRegisterRequest;
import com.ra.module5_project.model.dto.user.request.UserUpdatePasswordDto;
import com.ra.module5_project.model.dto.user.response.UserLoginResponse;
import com.ra.module5_project.model.entity.Role;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.repository.RoleRepository;
import com.ra.module5_project.repository.UserRepository;
import com.ra.module5_project.security.SecurityConfig;
import com.ra.module5_project.security.jwt.JWTProvider;
import com.ra.module5_project.security.principle.UserPrinciple;
import com.ra.module5_project.service.sendMail.ActiveAccount;
import com.ra.module5_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTProvider jwtProvider ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SecurityConfig securityConfig ;
    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Authentication authentication;
        authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),userLoginRequest.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userPrinciple);
        User user = userPrinciple.getUser();
        user.setStatusLogin(true);
        userRepository.save(user);
        return UserLoginResponse.builder()
                .username(userPrinciple.getUsername())
                .accessToken(token)
                .typeToken("Bearer")
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }

    @Override
    public User changePassword(UserUpdatePasswordDto userUpdatePasswordDto) {
//        Authentication authentication;
//        authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userUpdatePasswordDto.getUsername(),userUpdatePasswordDto.getPassword()));
//        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
//        return userPrinciple.getUser();
        return new User();
    }

    @Override
    public boolean register(UserRegisterRequest userRegisterRequest) {
        Random random = new Random();
        long code = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_000L);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByRoleName("USER"));
        User user = User.builder()
                .username(userRegisterRequest.getUsername())
                .password(securityConfig.passwordEncoder().encode(userRegisterRequest.getPassword()))
                .email(userRegisterRequest.getEmail())
                .phone(userRegisterRequest.getPhone())
                .address(userRegisterRequest.getAddress())
                .roles(roles)
                .status(false)
                .fullName(userRegisterRequest.getFullName())
                .created_at(new Date())
                .activeCode(code)
                .build();

        ActiveAccount.activeAccount(userRegisterRequest.getEmail(),code);
       User userNew = userRepository.save(user);
        if(userNew != null){
            return true ;
        }else {
            return false ;
        }
    }
}
