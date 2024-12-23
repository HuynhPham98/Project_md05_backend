package com.ra.module5_project.model.dto.booking.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingSearch {
    private LocalDate date ;
    private long movieId ;
    private long theaterId ;
    private long screenRoomId ;
    private long showTimeId ;
}
