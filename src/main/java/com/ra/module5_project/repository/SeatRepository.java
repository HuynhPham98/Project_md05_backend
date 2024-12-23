package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.Seat;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    @Query("select s from Seat s where s.seatName like %:search%")
    Page<Seat> findAllAndSearch(Pageable pageable , @Param("search") String search);

    @Query("select count(s) > 0 from Seat s where s.seatName = :seatName and s.screenRoom.id = :screenRoomId")
    boolean checkSeatNameExist(@Param("seatName") String seatName , @Param("screenRoomId") long screenRoomId);

    @Modifying
    @Transactional // Đảm bảo rằng phương thức này được gọi trong một giao dịch
    @Query("DELETE FROM Seat s WHERE s.screenRoom.id = :screenRoomId")
    void deleteSeatByScreenRoomId(@Param("screenRoomId") long screenRoomId);

    @Query("select s from Seat s where s.screenRoom.id = :screenRoomId")
    List<Seat> getSeatByScreenRoomId(@Param("screenRoomId") long screenRoomId);
}
