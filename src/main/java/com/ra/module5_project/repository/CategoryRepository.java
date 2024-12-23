package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByCategoryNameContaining(String keyword, Pageable pageable);
    @Query("select count(c) > 0 from Category  c where c.categoryName = :categoryName")
    boolean existsByCategoryName(String categoryName);

    @Query("select count(m) > 0 from Movie m join m.categories c where c.id = :categoryId")
    boolean existsByCategoryId(Long categoryId);

    @Query("select count(c.id) > 0 from Category c where c.categoryName like :categoryName AND c.id != :id")
    boolean existsByCategoryNameAndIdNot(String categoryName, Long id);

    Page<Category> findAllByStatus(boolean status, Pageable pageable);
}
