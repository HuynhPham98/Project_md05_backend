package com.ra.module5_project.model.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserLoginRequest {
    @NotBlank(message = "username can not blank")
    private String username ;
    @NotBlank(message = "password can not blank")
    private String password ;
}
