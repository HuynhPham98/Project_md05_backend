package com.ra.module5_project.model.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdatePasswordDto {
    @NotBlank
    private String oldPass ;
    @NotBlank
    private String newPass;
    @NotBlank
    private String confirmNewPass;
}
