package com.ra.module5_project.model.dto.Gift.response;

import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.Gift;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GiftPagination {
    private List<Gift> gifts ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
