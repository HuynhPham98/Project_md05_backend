package com.ra.module5_project.model.dto.screenRoom.request;

import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.model.entity.ScreenRoom;
import com.ra.module5_project.validator.EvenOrOdd;
import com.ra.module5_project.validator.NameExist;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScreenRoomRequest {
    @NotNull(message = "theater id can not null")
    @Min(1)
    private long theaterId ;

    @NotBlank(message = "screen number can not blank")
    private  String screenName ;

    @NotNull(message = "number of seat can not null")
    @EvenOrOdd(message = "number of seat is number even")
    @Min(2)
    private int numberColSeat ;

    @NotNull(message = "number of seat can not null")
    @Min(1)
    private int numberRowSeat ;

    @NotNull(message = "Is double seat can not null")
    private Boolean isDoubleSeat ;

}
