package com.ra.module5_project.model.dto.showTime.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.module5_project.model.entity.ShowTime;
import com.ra.module5_project.validator.CheckDateFutureOrPresent;
import com.ra.module5_project.validator.ShowTimeExist;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShowTimeRequestUpdate {
    @NotNull(message = "Movie id can not null")
    @Min(1)
    private long movieId ;

    @NotNull(message = "Screen room id can not null")
    @Min(1)
    private long screenRoomId ;

    @CheckDateFutureOrPresent(entityClass = ShowTime.class , fieldName = "showTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotBlank(message = "Show time can not blank")
    private String showTime ;

    @NotBlank(message = "Type movie can not blank")
    private String typeMovie;

    @NotNull(message = "Theater id can not null")
    @Min(1)
    private long theaterId ;
}
