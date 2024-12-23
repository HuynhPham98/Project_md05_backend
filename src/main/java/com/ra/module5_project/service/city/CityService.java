package com.ra.module5_project.service.city;

import com.ra.module5_project.model.dto.city.request.CityRequest;
import com.ra.module5_project.model.dto.city.request.CityRequestUpdate;
import com.ra.module5_project.model.dto.city.response.CityPagination;
import com.ra.module5_project.model.entity.City;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CityService {
    List<City> getAll();
    CityPagination findAll (Pageable pageable, String search);
    City addCity (CityRequest cityRequest);

    City updateCity(long id ,CityRequestUpdate cityRequestUpdate);

    City findById(long id);

    void deleteById(long id);
}
