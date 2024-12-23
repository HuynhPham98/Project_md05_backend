package com.ra.module5_project.model.dto.city.request;

import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.validator.NameExist;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CityRequestUpdate {
    @NotBlank(message = "city name can not blank")
    private String cityName ;
}
