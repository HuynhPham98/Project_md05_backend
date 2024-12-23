package com.ra.module5_project.model.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateDto {
    @NotBlank(message = "username can not blank")
    private String fullName ;

    @Pattern(regexp = "^0[35789][0-9]{8}$",message = "Phone number is not valid")
    @NotBlank(message = "Phone can not blank")
    private String phone ;

    @NotBlank(message = "Address can not blank")
    private String address ;

    private String image ;
}
