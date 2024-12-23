package com.ra.module5_project.model.dto.comboFoot.request;

import com.ra.module5_project.model.entity.ComboFood;
import com.ra.module5_project.validator.NameExist;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ComboFoodUpdate {
    @NotBlank(message = "Combo food can not blank")
    private String name ;

    @NotBlank(message = "Description can not blank")
    private String description ;

    @NotBlank(message = "image can not blank")
    private String image ;

    @NotNull(message = "Price can not null")
    @Min(0)
    private double price ;
}
