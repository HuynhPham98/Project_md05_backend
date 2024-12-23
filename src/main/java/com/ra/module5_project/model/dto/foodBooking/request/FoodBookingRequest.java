package com.ra.module5_project.model.dto.foodBooking.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodBookingRequest {
    private long comboFoodId ;
    private int quantity ;
}
