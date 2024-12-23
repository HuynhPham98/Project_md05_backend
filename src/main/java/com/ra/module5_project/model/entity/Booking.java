package com.ra.module5_project.model.entity;

import com.ra.module5_project.model.constant.StatusTicket;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    
    @Builder.Default
    private String serial_number = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "showTimeId",referencedColumnName = "id") // lịch chiếu
    private ShowTime showTime ;

    @ManyToOne
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user ;

    @Column(name = "totalSeat" , nullable = false)
    private int totalSeat ;

    @Column(name = "totalPriceMovie" , nullable = false)
    private  double totalPriceMovie ;

    @Column(name = "totalPriceFood" )
    private Double totalPriceFood ; // tổng tiền đò ăn

    private boolean status =true ;

    @Builder.Default
    private LocalDate created_at = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "giftId" ,referencedColumnName = "id")
    private Gift gift ;

    private double discount ;
}
