package com.ra.module5_project.model.dto.seat.request;

import com.ra.module5_project.model.entity.ScreenRoom;
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
public class SeatRequest {
    @NotNull(message = "Screen room id can not null")
    @Min(1)
    private long screenRoomId ;

    @NotBlank(message = "Seat name can not blank")
    private String seatName ;

    @NotBlank
    private String typeSeat ;

    @NotNull(message = "Status can not null")
    private int status  ;
}
