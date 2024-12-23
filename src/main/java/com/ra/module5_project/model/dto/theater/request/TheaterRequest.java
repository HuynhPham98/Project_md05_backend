package com.ra.module5_project.model.dto.theater.request;

import com.ra.module5_project.model.entity.Theater;
import com.ra.module5_project.validator.NameExist;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TheaterRequest {
    @NotBlank(message = "Theater name can not blank")
    @NameExist(message = "Theater name existed" , entityClass = Theater.class , fieldName = "name")
    private String name ;

    @NotBlank(message = "Address cannot blank")
    private String address ;

    @NotBlank(message = "Phone number can not blank")
    @Pattern(regexp = "^0[35789][0-9]{8}$",message = "Phone number invalid")
    private String phoneNumber ;

    @NotNull(message = "Number screen can not null")
    @Min(1)
    private int numberOfScreens ;
}
