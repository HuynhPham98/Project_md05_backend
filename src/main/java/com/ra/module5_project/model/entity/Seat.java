package com.ra.module5_project.model.entity;

import com.ra.module5_project.model.constant.TypeSeat;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne()
    @JoinColumn(name = "screenId" , referencedColumnName = "id")
    private ScreenRoom screenRoom ;

    @Column(name = "seatName" , nullable = false)
    private String seatName ;

    private TypeSeat typeSeat ;
    private int status = 1 ;
}
