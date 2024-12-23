package com.ra.module5_project.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "comboFood")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ComboFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "name" , nullable = false)
    private String name ;

    @Column(name = "description" , nullable = false)
    private String description ;

    @Column(name = "image" , nullable = false)
    private String image ;

    @Column(name = "price" , nullable = false)
    @Min(0)
    private double price ;
    private LocalDate created_at  = LocalDate.now();
}
