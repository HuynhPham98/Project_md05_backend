package com.ra.module5_project.service.seat;

import com.ra.module5_project.model.constant.TypeSeat;
import com.ra.module5_project.model.dto.seat.request.SeatRequest;
import com.ra.module5_project.model.dto.seat.response.SeatPagination;
import com.ra.module5_project.model.dto.seat.response.SeatResponse;
import com.ra.module5_project.model.entity.Seat;
import com.ra.module5_project.repository.ScreenRoomRepository;
import com.ra.module5_project.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository ;
    @Autowired
    private ScreenRoomRepository screenRoomRepository;

    @Override
    public List<Seat> getSeatByScreenId(long id) {
        return seatRepository.getSeatByScreenRoomId(id);
    }

    @Override
    public SeatPagination findAllAndSearch(Pageable pageable, String search) {
        Page<Seat> page = null ;
       if(search != null){
           page = seatRepository.findAllAndSearch(pageable, search);
       }else {
           page = seatRepository.findAll(pageable);
       }
       return SeatPagination.builder()
               .seats(page.getContent().stream().map(this::convertToSeatResponse).toList())
               .currentPage(page.getNumber())
               .size(page.getSize())
               .totalElement(page.getTotalElements())
               .totalPage(page.getTotalPages())
               .build();
    }

    @Override
    public SeatResponse save(SeatRequest seatRequest) {
        boolean checkSeatNameExist = seatRepository
                .checkSeatNameExist(seatRequest.getSeatName(), seatRequest.getScreenRoomId());
        if(checkSeatNameExist){
            return null ;
        }else {
            Seat seat = convertToSeat(seatRequest);
            Seat newSeat = seatRepository.save(seat);
            return convertToSeatResponse(newSeat);
        }
    }

    @Override
    public SeatResponse update(long id ,SeatRequest seatRequest) {
        SeatResponse seatOld = findById(id);
        boolean checkSeatNameExist = seatRepository.checkSeatNameExist(seatRequest.getSeatName(), seatRequest.getScreenRoomId());
        if(checkSeatNameExist && seatRequest.getSeatName().equals(seatOld.getSeatName())){
            checkSeatNameExist = false ;
        }
        if(checkSeatNameExist){
            return null ;
        }else {
            Seat newSeat = convertToSeat(seatRequest);
            newSeat.setId(seatOld.getId());
            return convertToSeatResponse(seatRepository.save(newSeat));
        }
    }

    @Override
    public boolean updateAll(List<Seat> seats) {
        seatRepository.saveAll(seats);
        return true ;
    }

    @Override
    public void deleteById(long id) {
        SeatResponse seat = findById(id);
        seatRepository.deleteById(id);
    }

    @Override
    public SeatResponse findById(long id) {
       Seat seat = seatRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found seat"));
       return convertToSeatResponse(seat);
    }

    @Override
    public void autoCreateSeat(long screenRoomId, int numberSeat) {

    }

    @Override
    public void autoDeleteSeat(long screenRoomId) {

    }

    Seat convertToSeat(SeatRequest seatRequest){
        TypeSeat typeSeat;
        try {
            typeSeat = TypeSeat.valueOf(seatRequest.getTypeSeat().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid type seat: " + seatRequest.getTypeSeat());
        }
        return Seat.builder()
                .seatName(seatRequest.getSeatName())
                .status(seatRequest.getStatus())
                .typeSeat(typeSeat)
                .screenRoom(screenRoomRepository.findById(seatRequest.getScreenRoomId())
                        .orElseThrow(() -> new NoSuchElementException("Not found screen room")))
                .build();
    }

    SeatResponse convertToSeatResponse(Seat seat){
        return SeatResponse.builder()
                .id(seat.getId())
                .screenName(seat.getScreenRoom().getScreenName())
                .seatName(seat.getSeatName())
                .status(seat.getStatus())
                .typeSeat(seat.getTypeSeat())
                .build();
    }
}
