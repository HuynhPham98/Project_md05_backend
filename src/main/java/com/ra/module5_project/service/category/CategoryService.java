package com.ra.module5_project.service.category;

import com.ra.module5_project.exception.BadRequestException;
import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.category.CategoryDTO;
import com.ra.module5_project.model.dto.category.CategoryUpdateDTO;
import com.ra.module5_project.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCate();
    Page<Category> findAll(Pageable pageable);
    Category create(CategoryDTO categoryDTO);
    Category findById(Long id);
    Category update(CategoryUpdateDTO categoryUpdateDTO, Long id) throws BadRequestException;
    void delete(Long id) throws CustomException;
    Page<Category> findByCategoryName(String keyword, Pageable pageable);
    Page<Category>findAllByStatus(Pageable pageable);
}
