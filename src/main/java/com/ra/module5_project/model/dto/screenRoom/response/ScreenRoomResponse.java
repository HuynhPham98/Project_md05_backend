package com.ra.module5_project.model.dto.screenRoom.response;

import com.ra.module5_project.model.entity.Theater;
import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScreenRoomResponse {
    private long id ;
    private String theaterName ;
    private  String screenName ;
    private int numberRowSeat ;
    private int numberColSeat ;
    private boolean isDoubleSeat ;
}
