package com.ra.module5_project.model.dto.bookingSeat.request;

import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.Seat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SecondaryRow;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingSeatRequest {
    private long booking ;
    private long seatId ;
    private int quantity ;
    private double price ;
    private LocalDateTime created_date ;
}
