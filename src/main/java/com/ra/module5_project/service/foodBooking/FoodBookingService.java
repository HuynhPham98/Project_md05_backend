package com.ra.module5_project.service.foodBooking;

import com.ra.module5_project.model.entity.FoodBooking;

import java.util.List;

public interface FoodBookingService {
    List<FoodBooking> findAll() ;
    FoodBooking save(FoodBooking foodBooking);
    FoodBooking findById(long foodBookingId);
    void deleteById(long id);
    List<FoodBooking> getFoodByBookingId(long bookingId);
}
