package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
    @Query("select t from Theater t where t.name like %:search%")
    Page<Theater> findAllAndSearchName(Pageable pageable , @Param("search") String search);

    @Query("select count(t) > 0 from Theater  t where t.name = :theaterName")
    boolean checkTheaterNameExist(@Param("theaterName") String theaterName);

    @Query("select t from Theater  t where t.address = :cityName")
    List<Theater> getTheaterByCity(@Param("cityName") String cityName);


}
