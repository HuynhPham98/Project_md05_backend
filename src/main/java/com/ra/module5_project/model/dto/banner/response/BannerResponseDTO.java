package com.ra.module5_project.model.dto.banner.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BannerResponseDTO {

    private Long id;

    private String imageURL ;

    private String title;
}
