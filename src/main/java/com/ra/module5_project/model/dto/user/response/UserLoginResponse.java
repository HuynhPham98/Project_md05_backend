package com.ra.module5_project.model.dto.user.response;

import com.ra.module5_project.model.entity.Role;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginResponse {
    private String username ;
    private String accessToken;
    private String typeToken;
    private Set<Role> roles;
}
