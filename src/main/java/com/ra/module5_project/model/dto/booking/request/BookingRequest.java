package com.ra.module5_project.model.dto.booking.request;

import com.ra.module5_project.model.dto.foodBooking.request.FoodBookingRequest;
import com.ra.module5_project.model.entity.ShowTime;
import com.ra.module5_project.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BookingRequest {
    @NotNull(message = "Show time id can not null")
    @Min(1)
    private long showTimeId ;

    @NotNull(message = "List seat can not null")
    @Size(min = 1, message = "List seat must contain at least one seat")
    private List<Long> listSeatId ;

    private double totalPriceFood ;

    private List<FoodBookingRequest> foodBookingRequests;

    private long giftId ;

    private double discount ;
}
