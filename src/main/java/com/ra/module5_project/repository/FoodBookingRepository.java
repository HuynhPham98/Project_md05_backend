package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.ComboFood;
import com.ra.module5_project.model.entity.FoodBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodBookingRepository extends JpaRepository<FoodBooking,Long> {
    @Query("select fb from FoodBooking fb where fb.booking.id = :bookingId")
    List<FoodBooking> getComboFoodByBookingId(@Param("bookingId") long bookingId);
}
