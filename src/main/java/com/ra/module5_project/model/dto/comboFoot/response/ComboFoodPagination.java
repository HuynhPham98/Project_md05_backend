package com.ra.module5_project.model.dto.comboFoot.response;

import com.ra.module5_project.model.entity.BookingSeat;
import com.ra.module5_project.model.entity.ComboFood;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ComboFoodPagination {
    private List<ComboFood> comboFoods ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
