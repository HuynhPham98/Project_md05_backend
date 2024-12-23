package com.ra.module5_project.service.category;

import com.ra.module5_project.exception.BadRequestException;
import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.exception.NoResourceFoundException;
import com.ra.module5_project.exception.ResourceNotFoundException;
import com.ra.module5_project.model.dto.category.CategoryDTO;
import com.ra.module5_project.model.dto.category.CategoryUpdateDTO;
import com.ra.module5_project.model.entity.Category;
import com.ra.module5_project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCate() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category create(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName((categoryDTO.getCategoryName()));
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(true);
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) throws NoResourceFoundException {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new NoResourceFoundException("Danh mục không tồn tại!");
        }
        return category;
    }

    @Override
    public Category update(CategoryUpdateDTO categoryUpdateDTO, Long id) throws BadRequestException {
        Category category = findById(id);

        // Nếu tên mới khác với tên hiện tại, kiểm tra sự tồn tại
        if (!category.getCategoryName().equals(categoryUpdateDTO.getCategoryName())) {
            boolean check = categoryRepository.existsByCategoryNameAndIdNot(categoryUpdateDTO.getCategoryName().trim(), id);
            if (check) {
                throw new BadRequestException("Danh mục đã tồn tại");
            }
            category.setCategoryName(categoryUpdateDTO.getCategoryName()); // Trim các khoảng trắng nếu có
        }
        category.setDescription(categoryUpdateDTO.getDescription());
        category.setStatus(true);
        return categoryRepository.save(category);


    }


    @Override
    public void delete(Long id) throws CustomException {
        boolean check = categoryRepository.existsByCategoryId(id);
        if (check) {
            throw new CustomException("Danh mục này đã có phim rồi");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> findByCategoryName(String keyword, Pageable pageable) {
        return categoryRepository.findByCategoryNameContaining(keyword, pageable);
    }

    @Override
    public Page<Category> findAllByStatus(Pageable pageable) {
        return categoryRepository.findAllByStatus(true,pageable);
    }
}
