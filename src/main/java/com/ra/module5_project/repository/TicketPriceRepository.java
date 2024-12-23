package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;

public interface TicketPriceRepository extends JpaRepository<TicketPrice,Long> {
    @Query("select tp from TicketPrice tp where tp.typeSeat = :typeSeat and tp.typeMovie = :typeMovie and tp.startTime = :startTime and tp.endTime = :endTime and tp.dayOfWeeks = :dayOfWeeks and tp.isHoliday = :isHoliday")
    TicketPrice findTicketPrice(@Param("typeSeat") String typeSeat,
                                @Param("typeMovie") String typeMovie,
                                @Param("startTime") LocalTime startTime,
                                @Param("endTime") LocalTime endTime,
                                @Param("dayOfWeeks") int dayOfWeeks,
                                @Param("isHoliday") boolean isHoliday
                                );
}

