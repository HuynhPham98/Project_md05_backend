package com.ra.module5_project.controller.admin;

import com.ra.module5_project.exception.BadRequestException;
import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.category.CategoryDTO;
import com.ra.module5_project.model.dto.category.CategoryUpdateDTO;
import com.ra.module5_project.model.entity.Category;
import com.ra.module5_project.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/admin/categories")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "") String search, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Category> categories;
        if(search == null || search.isEmpty()) {
            categories = categoryService.findAll(pageable);
        } else {
            categories = categoryService.findByCategoryName(search, pageable);
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<Category> categories = categoryService.findAllCate();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.create(categoryDTO);
        return ResponseEntity
                .created(URI.create("/categories/" + category.getId()))
                .body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) throws BadRequestException {
        Category updateCategory = categoryService.update(categoryUpdateDTO, id);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws CustomException {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
