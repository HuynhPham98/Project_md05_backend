package com.ra.module5_project.model.dto.user.response;

import com.ra.module5_project.model.entity.Role;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserResponse {
    private long id;
    private String username ;
    private String email;
    private String fullName ;
    private boolean status ;
    private String avatar;
    private Set<Role> roles;
    private String phone ;
    private String address ;
}