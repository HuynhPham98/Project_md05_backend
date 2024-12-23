package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.ComboFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComboFoodRepository extends JpaRepository<ComboFood,Long> {
    @Query("select count(cb) > 0 from ComboFood  cb where cb.name = :name")
    boolean checkNameExist(@Param("name") String name);
}
