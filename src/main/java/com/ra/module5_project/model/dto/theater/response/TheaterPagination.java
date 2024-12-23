package com.ra.module5_project.model.dto.theater.response;

import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.model.entity.Theater;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TheaterPagination {
    private List<Theater> theaters ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
