package com.ra.module5_project.model.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsUpdateDTO {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotBlank(message = "Ảnh không được để trống")
    private String image;
    @NotNull(message = "Ngày bắt đầu được để trống")
    private LocalDate start_time;
    @NotNull(message = "Ngày kết thúc được để trống")
    private LocalDate end_time;
}
