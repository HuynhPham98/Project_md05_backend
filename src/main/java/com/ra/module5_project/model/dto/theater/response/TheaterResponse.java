package com.ra.module5_project.model.dto.theater.response;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TheaterResponse {
    private long id ;

    private String name ;

    private String address ;

    private String phoneNumber ;

    private int numberOfScreens ;
}
