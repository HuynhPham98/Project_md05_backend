package com.ra.module5_project.model.dto.showTime.response;

import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.model.entity.ShowTime;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShowTimePagination {
    private List<ShowTime> showTimes ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
