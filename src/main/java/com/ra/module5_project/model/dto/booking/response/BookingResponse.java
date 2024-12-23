package com.ra.module5_project.model.dto.booking.response;

import com.ra.module5_project.model.entity.ShowTime;
import com.ra.module5_project.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {
    private long id ;

    private long showTimeId ;

    private long userId ;

    private int totalSeat ;

    private  double totalPriceMovie ;

    private double totalPriceFood ;

    private boolean status ;
    private LocalDate created_at ;
}
