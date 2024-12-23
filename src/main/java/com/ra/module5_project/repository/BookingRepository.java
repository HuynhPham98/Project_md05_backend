package com.ra.module5_project.repository;


import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("select b from Booking  b where b.user.id = :userId")
    List<Booking> findAllByUserId(@Param("userId") long userId);


    @Query("select bk from Booking bk where (:date IS NULL OR bk.created_at = :date) and " +
            "(:movieId IS NULL OR bk.showTime.movie.id = :movieId) AND " +
            "(:theaterId IS NULL OR bk.showTime.theater.id = :theaterId) AND " +
            "(:screenRoomId IS NULL OR bk.showTime.screenRoom.id = :screenRoomId) AND :showTimeId is null or bk.showTime.id = :showTimeId")
    Page<Booking> findAllAndSearchAndPagination(Pageable pageable, @Param("date") LocalDate date, @Param("movieId") Long movieId, @Param("theaterId") Long theaterId, @Param("screenRoomId") Long screenRoomId, @Param("showTimeId") Long showTimeId);


    @Query("select month(b.created_at) as month, sum(b.totalPriceMovie + b.totalPriceFood) as revenue " +
            "from Booking b " +
            "where year(b.created_at) = :year " +
            "group by month(b.created_at) " + //Nhóm các bản ghi theo từng tháng.
            "order by month(b.created_at)") // Sắp xếp kết quả theo thứ tự tháng từ 1 đến 12.
    List<Object[]> revenueByYear(@Param("year") int year);

    @Query("select u from User u join Booking b on u.id = b.user.id group by u.id order by sum(b.totalPriceMovie + b.totalPriceFood) desc limit 1")
    User findTopUserByRevenue();

    @Query("select sum(b.totalPriceMovie + b.totalPriceFood) from Booking b")
    Double totalPrice();
}
