package com.ra.module5_project.model.dto.banner.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BannerRequestDTO {

    @NotBlank(message = "Ảnh không được để trống")
    private String imageUrl ;

    @NotBlank(message = "Tiêu đề không được để trống")
    private String title ;

}
