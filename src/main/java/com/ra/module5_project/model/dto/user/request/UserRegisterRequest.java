package com.ra.module5_project.model.dto.user.request;

import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.validator.NameExist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRegisterRequest {
    @Pattern(regexp = "^[A-Za-z0-9]{6,100}$",
             message = "Username is not valid")
    @NotBlank(message = "Username can not blank")
    @NameExist(message = "username duplicate",entityClass = User.class ,fieldName = "username")
    private String username ;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$",
            message = "Email is not valid"
    )
    @NotBlank(message = "Email can not blank")
    @NameExist(message = "email existed",entityClass = User.class,fieldName = "email")
    private String email;

    @NotBlank(message = "Full name can not blank")
    private String fullName ;

    @NotBlank(message = "Password can not blank")
    private String password;

    @Pattern(regexp = "^0[35789][0-9]{8}$",message = "Phone number is not valid")
    @NotBlank(message = "Phone can not blank")
    private String phone ;

    @NotBlank(message = "Address can not blank")
    private String address ;

}
