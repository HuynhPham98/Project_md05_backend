package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.ShowTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime,Long> {
    @Query("select s from ShowTime s where s.movie.id = :movieId and s.theater.id = :theaterId and s.screenRoom.id = :screenRoomId and s.id = :showTimeId")
    Page<ShowTime> findAllAndSearch(Pageable pageable , @Param("movieId") Long movieId ,
                                        @Param("theaterId") Long theaterId,
                                        @Param("screenRoomId") Long screenRoomId ,
                                        @Param("showTimeId") Long showTimeId
                                    );

    @Query("SELECT COUNT(s) > 0 FROM ShowTime s WHERE "
            + "FUNCTION('YEAR', s.showTime) = FUNCTION('YEAR', :showTime) AND "
            + "FUNCTION('MONTH', s.showTime) = FUNCTION('MONTH', :showTime) AND "
            + "FUNCTION('DAY', s.showTime) = FUNCTION('DAY', :showTime) AND "
            + "FUNCTION('HOUR', s.showTime) = FUNCTION('HOUR', :showTime) and s.theater.id = :theaterId and s.screenRoom.id = :screenRoomId")
    boolean checkShowTimeExist(@Param("showTime") LocalDateTime showTime,@Param("theaterId") long theaterId,@Param("screenRoomId") long screenRoomId);

    @Query("select st from ShowTime st where st.movie.id = :movieId and st.showDate = :date and st.theater.name = :theaterName")
    List<ShowTime> getShowTimeByMovieAndDate(@Param("movieId") long movieId ,@Param("date") LocalDate date , @Param("theaterName") String theaterName);

    @Query("select st from ShowTime st where st.screenRoom.id = :screenRoomId")
    List<ShowTime> getShowTimesByScreenRoomId(@Param("screenRoomId") long screenRoomId);

    @Query("select st from ShowTime st where st.showDate >= :localDate")
    Page<ShowTime> findAll(Pageable pageable , LocalDate localDate);
}
