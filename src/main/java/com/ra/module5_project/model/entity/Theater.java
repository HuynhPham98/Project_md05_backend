package com.ra.module5_project.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "theater") // rạp chiếu
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "name" , nullable = false)
    private String name ;

    @Column(name = "address")
    private String address ;

    @Column(name = "phoneNumber")
    private String phoneNumber ;

    @Column(name = "numberOfScreens") // số phòng chiếu
    private int numberOfScreens ;
}
