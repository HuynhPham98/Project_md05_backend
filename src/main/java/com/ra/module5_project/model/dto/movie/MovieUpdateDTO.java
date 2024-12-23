package com.ra.module5_project.model.dto.movie;

import com.ra.module5_project.validator.UniqueMovieName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieUpdateDTO {
    @NotBlank(message = "Tên phim không được để trống!")
    private String movieName;

    @Min(value = 1,message = "Thời lượng phim lớn hơn 0")
    private int duration; //Thời lượng phim

    @NotNull(message = "Ngày phát hành không được để trống")
    private LocalDate releaseDate; //Ngày phát hành

    @NotBlank(message = "Đạo diễn không được để trống")
    private String director; //Đạo diễn

    @NotBlank(message = "Diễn viên chính không được để trống")
    private String cast; //Diễn viên chính

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Ngôn ngữ không được để trống")
    private String language;
    private String poster;

    @NotNull(message = "Danh sách thể loại không được để trống")
    private Set<Long> categoryIds;
}
