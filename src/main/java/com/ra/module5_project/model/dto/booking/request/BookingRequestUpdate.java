package com.ra.module5_project.model.dto.booking.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class BookingRequestUpdate {
    @NotNull(message = "Show time id can not null")
    @Min(1)
    private long showTimeId ;

    @NotNull(message = "List seat can not null")
    @Size(min = 1, message = "List seat must contain at least one seat")
    private List<Long> listSeatId ;

    private double totalPriceFood ;
}
