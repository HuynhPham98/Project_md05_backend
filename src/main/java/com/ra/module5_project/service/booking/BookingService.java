package com.ra.module5_project.service.booking;

import com.ra.module5_project.model.dto.booking.request.BookingRequest;
import com.ra.module5_project.model.dto.booking.response.BookingPagination;
import com.ra.module5_project.model.dto.booking.response.BookingResponse;
import com.ra.module5_project.model.dto.foodBooking.request.FoodBookingRequest;
import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.security.principle.UserPrinciple;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
    BookingPagination findAll(Pageable pageable, LocalDate date,Long movieId , Long theaterId , Long screenRoomId , Long showTimeId);
    Booking save(BookingRequest bookingRequest, UserPrinciple userPrinciple);
    Booking findById(long bookingId);
    List<Booking> findAllByUserId(long userId);


    Map<Integer,Double> getRevenueByYear(int year);

    Double totalPrice();

    List<Booking> findAllBookingByTopUser();

    Double convertSeatToMoney(Long showTimeId ,List<Long> seatIds);
    Double seatToMoney(long showTimeId , long seatId);
    BookingResponse convertToResponse(Booking booking);
}
