package com.ra.module5_project.model.dto.booking.response;

import com.ra.module5_project.model.entity.Booking;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingPagination {
    private List<Booking> bookings ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
