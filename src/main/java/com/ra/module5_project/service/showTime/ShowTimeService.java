package com.ra.module5_project.service.showTime;

import com.ra.module5_project.model.dto.showTime.request.ShowTimeRequest;
import com.ra.module5_project.model.dto.showTime.request.ShowTimeRequestUpdate;
import com.ra.module5_project.model.dto.showTime.response.ShowTimePagination;
import com.ra.module5_project.model.entity.ShowTime;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ShowTimeService {
    ShowTimePagination findAllAndSearch(Pageable pageable,Long movieId ,Long theaterId , Long screenRoomId , Long showTimeId );
    ShowTime save(ShowTimeRequest showTimeRequest);
    ShowTime update(long idShowTime , ShowTimeRequestUpdate showTimeRequestUpdate);
    ShowTime findById(long id);
    void deleteById(long id);
    ShowTime convertDTOToShowTime(ShowTimeRequest showTimeRequest);
    List<ShowTime> getShowTimeByMovieAndDate(long movieId, LocalDate date,String theaterName);
    List<ShowTime> getShowTimeByScreenRoom(long screenRoomId);
}
