package com.ra.module5_project.model.entity;

import com.ra.module5_project.model.constant.TypeMovie;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "showTime") // lịch chiếu
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "screenRoomId" , referencedColumnName = "id")
    private ScreenRoom screenRoom ;
    @ManyToOne
    @JoinColumn(name = "movieId",referencedColumnName = "id")
    private Movie movie ;

    @Column(name = "showDate",nullable = false)
    private LocalDate showDate ;

    @Column(name = "showTime" ,nullable = false)
    private LocalDateTime showTime ;

    @ManyToOne
    @JoinColumn(name = "theaterId" , referencedColumnName = "id")
    private Theater theater ;

    @Enumerated(EnumType.STRING)
    private TypeMovie typeMovie;

    private LocalDateTime created_date = LocalDateTime.now();
}
