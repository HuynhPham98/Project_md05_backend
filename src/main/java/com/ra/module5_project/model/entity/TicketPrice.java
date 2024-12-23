package com.ra.module5_project.model.entity;

import com.ra.module5_project.model.constant.TypeSeat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ticketPrices")
public class TicketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "typeSeat",nullable = false)
    private String typeSeat ;

    @Column(name = "typeMovie", nullable = false)
    private String typeMovie;  //2D , 3D

    @Column(name = "startTime", nullable = false)
    private LocalTime startTime ;

    @Column(name = "endTime", nullable = false)
    private LocalTime endTime ;

    @Column(name = "price" , nullable = false)
    private double price ;

    @Column(name = "dayOfWeeks" , nullable = false)
    private int dayOfWeeks ;

    private boolean isHoliday ;

}
