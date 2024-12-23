package com.ra.module5_project.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "screenRoom")
public class ScreenRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "theaterId" , referencedColumnName = "id")//id rạp chiếu
    private Theater theater ;

    @Column(name = "screenName" , nullable = false) // số phòng chiếu
    private  String screenName ;

    @Column(name = "numberRowSeat" , nullable = false) // số cột ghế
    private int numberRowSeat ;

    @Column(name = "numberColSeat" , nullable = false) // số hàng ghế
    private int numberColSeat ;

    @Column(name = "isDoubleSeat" , nullable = false) // có  hàng ghế đôi
    private boolean isDoubleSeat = true;

}
