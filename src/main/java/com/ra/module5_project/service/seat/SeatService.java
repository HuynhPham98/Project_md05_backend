package com.ra.module5_project.service.seat;

import com.ra.module5_project.model.dto.seat.request.SeatRequest;
import com.ra.module5_project.model.dto.seat.response.SeatPagination;
import com.ra.module5_project.model.dto.seat.response.SeatResponse;
import com.ra.module5_project.model.entity.Seat;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SeatService {
    List<Seat> getSeatByScreenId(long id);
    SeatPagination findAllAndSearch(Pageable pageable , String search);
    SeatResponse save(SeatRequest seatRequest);
    SeatResponse update(long id ,SeatRequest seatRequest);
    boolean updateAll(List<Seat> seats);
    void deleteById(long id);
    SeatResponse findById(long id);
    void autoCreateSeat(long screenRoomId,int numberSeat);
    void autoDeleteSeat(long screenRoomId);
}
