package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CityRepository extends JpaRepository<City,Long> {
    @Query("select c from City c where c.cityName like %:search%")
    Page<City> findAllAndSearchName (Pageable pageable , String search);

    @Query("select count(c) > 0 from City c where c.cityName = :_cityName")
    boolean existsByCityName(@Param("_cityName") String cityName);


}
