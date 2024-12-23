package com.ra.module5_project.model.dto.showTime.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.module5_project.model.constant.TypeMovie;
import com.ra.module5_project.model.entity.Movie;
import com.ra.module5_project.model.entity.ShowTime;
import com.ra.module5_project.model.entity.Theater;
import com.ra.module5_project.validator.CheckDateFutureOrPresent;
import com.ra.module5_project.validator.ShowTimeExist;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ShowTimeRequest {
    @NotNull(message = "Movie id can not null")
    @Min(1)
    private long movieId ;

    @NotNull(message = "Screen room id can not null")
    @Min(1)
    private long screenRoomId ;

    @NotBlank(message = "Show time can not blank")
    @CheckDateFutureOrPresent(entityClass = ShowTime.class , fieldName = "showTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String showTime ;

    @NotBlank(message = "Type movie can not blank")
    private String typeMovie;

    @NotNull(message = "Theater id can not null")
    @Min(1)
    private long theaterId ;
}
