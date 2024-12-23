package com.ra.module5_project.model.dto.category;

import com.ra.module5_project.validator.UniqueCategoryName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryUpdateDTO {
    @NotBlank(message = "Tên danh mục không được để trống!")
    private String categoryName;
    @NotBlank(message = "Mô tả danh mục không được để trống!")
    private String description;
}
