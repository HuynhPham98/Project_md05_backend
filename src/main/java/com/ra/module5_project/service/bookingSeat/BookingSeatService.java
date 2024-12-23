package com.ra.module5_project.service.bookingSeat;

import com.ra.module5_project.model.dto.bookingSeat.request.BookingSeatRequest;
import com.ra.module5_project.model.dto.bookingSeat.response.BookingSeatPagination;
import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.BookingSeat;
import com.ra.module5_project.model.entity.Seat;

import java.util.List;

public interface BookingSeatService {
    List<BookingSeat> findAllByBookingId(long bookingId);
    BookingSeat save(BookingSeat bookingSeat);
    BookingSeat findById(long id) ;
    List<Seat> getListSeatSold(long showTimeId);
    void deleteByBookingId(long bookingId);
    List<BookingSeat> getBookingSeatByBookingId(long bookingId);
}
