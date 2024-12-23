package com.ra.module5_project.model.dto.theater.request;

import com.ra.module5_project.model.entity.Theater;
import com.ra.module5_project.validator.NameExist;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TheaterRequestUpdate {
    @NotBlank(message = "Theater name can not blank")
    private String name ;

    @NotBlank(message = "Address cannot blank")
    private String address ;

    @NotBlank(message = "Phone number can not blank")
    @Pattern(regexp = "^0[35789][0-9]{8}$",message = "Phone number invalid")
    private String phoneNumber ;

    @NotNull(message = "Number screen can not null")
    @Min(1)
    @Max(100)
    private int numberOfScreens ;
}
