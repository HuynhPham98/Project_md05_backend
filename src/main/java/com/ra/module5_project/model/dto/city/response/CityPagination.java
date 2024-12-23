package com.ra.module5_project.model.dto.city.response;

import com.ra.module5_project.model.entity.City;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CityPagination {
    private List<City> cities ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
