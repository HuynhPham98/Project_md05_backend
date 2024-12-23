package com.ra.module5_project.model.dto.Gift.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GiftRequest {
    @NotBlank(message = "Gift name can not blank")
    private String giftName ;

    @NotBlank(message = "Description can not blank")
    private String description ;

    @NotBlank(message = "Image can not blank")
    private String image ;

    @NotNull(message = "ExpiredDate can not null")
    private LocalDate expiredDate ;
}
