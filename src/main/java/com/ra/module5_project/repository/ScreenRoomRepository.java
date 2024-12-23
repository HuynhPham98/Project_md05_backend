package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.ScreenRoom;
import com.ra.module5_project.model.entity.ShowTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScreenRoomRepository extends JpaRepository<ScreenRoom,Long> {
    @Query("select s from ScreenRoom s where s.screenName like %:search%")
    Page<ScreenRoom> findAllAndSearch(Pageable pageable , @Param("search") String search);

    @Query("select st from ShowTime st where " +
            "(:movieId IS NULL OR st.movie.id = :movieId) AND " +
            "(:theaterId IS NULL OR st.theater.id = :theaterId) AND " +
            "(:screenRoomId IS NULL OR st.screenRoom.id = :screenRoomId) AND " +
            "(st.showDate > :now OR st.showDate = :now)")
    Page<ShowTime> findAllAndSearchAndPagination(Pageable pageable, @Param("movieId") Long movieId, @Param("theaterId") Long theaterId, @Param("screenRoomId") Long screenRoomId, @Param("now")LocalDate now);

    @Query("select count(s) > 0 from ScreenRoom s where s.screenName = :screenName and s.theater.id = :theaterId")
    boolean existsByScreenName(@Param("screenName") String screenName, @Param("theaterId") long theaterId);

    @Query("select s from ScreenRoom s where s.theater.id = :theaterId")
    List<ScreenRoom> getScreenRoomByTheaterId(@Param("theaterId") long id);
}
