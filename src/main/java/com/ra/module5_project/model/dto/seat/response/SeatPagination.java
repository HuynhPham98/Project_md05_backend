package com.ra.module5_project.model.dto.seat.response;

import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.model.entity.Seat;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SeatPagination {
    private List<SeatResponse> seats ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
