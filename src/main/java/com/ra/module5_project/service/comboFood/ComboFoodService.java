package com.ra.module5_project.service.comboFood;

import com.ra.module5_project.model.dto.comboFoot.request.ComboFoodRequest;
import com.ra.module5_project.model.dto.comboFoot.request.ComboFoodUpdate;
import com.ra.module5_project.model.dto.comboFoot.response.ComboFoodPagination;
import com.ra.module5_project.model.entity.ComboFood;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComboFoodService {
    ComboFoodPagination findAll(Pageable pageable);
    ComboFood save(ComboFoodRequest comboFoodRequest);
    ComboFood update(long id ,ComboFoodUpdate comboFoodUpdate);
    ComboFood findById(long id);
    void deleteById(long id);
    ComboFood convertRequest(ComboFoodRequest comboFoodRequest);
    ComboFood convertUpdate (ComboFoodUpdate comboFoodUpdate);
    ComboFood updateImage(ComboFood comboFood);
    List<ComboFood> userFindAll();

}
