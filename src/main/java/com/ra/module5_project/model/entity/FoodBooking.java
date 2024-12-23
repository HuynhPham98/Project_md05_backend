package com.ra.module5_project.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "food_booking")
public class FoodBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;


   @ManyToOne
   @JoinColumn(name = "bookingId" , referencedColumnName = "id")
    private Booking booking ;

   @ManyToOne
    @JoinColumn(name = "comboFoodId" ,referencedColumnName = "id")
    private ComboFood comboFood ;

   private int quantity ;
   private double price ;
}
