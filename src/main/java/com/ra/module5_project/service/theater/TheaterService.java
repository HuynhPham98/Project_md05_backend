package com.ra.module5_project.service.theater;

import com.ra.module5_project.model.dto.theater.request.TheaterRequest;
import com.ra.module5_project.model.dto.theater.request.TheaterRequestUpdate;
import com.ra.module5_project.model.dto.theater.response.TheaterPagination;
import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.model.entity.Theater;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TheaterService {
    TheaterPagination findAllAndSearch(Pageable pageable , String search);
    Theater save(TheaterRequest theaterRequest);
    Theater update(long id , TheaterRequestUpdate theaterRequestUpdate);
    void deleteById(long id);
    Theater findById(long id);
    boolean checkNameExist(String name);
    List<Theater> getTheaters();
    List<Theater> getTheaterByCity(String cityName);
}
