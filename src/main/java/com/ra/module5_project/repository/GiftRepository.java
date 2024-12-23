package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GiftRepository extends JpaRepository<Gift,Long> {
    @Query("select g from Gift g where g.expiredDate >= :now")
    List<Gift> getListCurrent(@Param("now") LocalDate now);
}
