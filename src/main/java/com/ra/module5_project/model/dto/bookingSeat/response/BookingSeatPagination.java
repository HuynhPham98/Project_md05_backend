package com.ra.module5_project.model.dto.bookingSeat.response;

import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.BookingSeat;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingSeatPagination {
    private List<BookingSeat> bookingSeats ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
