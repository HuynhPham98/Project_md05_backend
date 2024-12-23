package com.ra.module5_project.service.screenRoom;

import com.ra.module5_project.model.constant.TypeSeat;
import com.ra.module5_project.model.dto.screenRoom.request.ScreenRoomRequest;
import com.ra.module5_project.model.dto.screenRoom.response.ScreenRoomPagination;
import com.ra.module5_project.model.dto.screenRoom.response.ScreenRoomResponse;
import com.ra.module5_project.model.entity.ScreenRoom;
import com.ra.module5_project.model.entity.Seat;
import com.ra.module5_project.model.entity.Theater;
import com.ra.module5_project.repository.ScreenRoomRepository;
import com.ra.module5_project.repository.SeatRepository;
import com.ra.module5_project.repository.TheaterRepository;
import com.ra.module5_project.service.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ScreenRoomServiceImpl implements ScreenRoomService{
    List<String> nameRowSeats = new ArrayList<>(Arrays.asList("0","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","Ư","X","Y","Z"));

    @Autowired
    private ScreenRoomRepository screenRoomRepository ;
    @Autowired
    private TheaterRepository theaterRepository ;
    @Autowired
    private SeatRepository seatRepository;


    @Override
    public ScreenRoomPagination findAllAndSearch(Pageable pageable, String search) {
        Page<ScreenRoom> page = null ;
        if(search != null){
            page = screenRoomRepository.findAllAndSearch(pageable, search);
        }else {
            page = screenRoomRepository.findAll(pageable);
        }
        return ScreenRoomPagination.builder()
                .screenRooms(page.getContent())
                .currentPage(page.getNumber())
                .size(page.getSize())
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public ScreenRoomResponse save(ScreenRoomRequest screenRoomRequest) {
        boolean checkScreenNameExist = screenRoomRepository.existsByScreenName(screenRoomRequest.getScreenName(),screenRoomRequest.getTheaterId());
        if(checkScreenNameExist){
            return null ;
        }
        else {
            if(!checkAddScreenRoom(screenRoomRequest.getTheaterId())){
                return null ;
            }else {
                ScreenRoom screenRoom = convertToScreenRoom(screenRoomRequest);
                ScreenRoom newScreenRoom = screenRoomRepository.save(screenRoom);

                // xử lý tạo ghế trong phòng chiếu

                addSeatForScreenRoom(newScreenRoom.getId(), newScreenRoom.getNumberRowSeat(), newScreenRoom.getNumberColSeat(), newScreenRoom.isDoubleSeat());

                return convertToScreenRoomResponse(newScreenRoom);
            }
        }
    }

    public void addSeatForScreenRoom(long idRoom ,int numberRow , int numberCol , boolean isDoubleSeat){

        for(int i = 1 ; i <= numberRow ; i++){
            for(int j = 1 ; j <= numberCol ; j++){
                Seat seat = Seat.builder()
                        .screenRoom(screenRoomRepository.findById(idRoom).orElseThrow(() -> new NoSuchElementException("Not found screen room")))
                        .seatName(nameRowSeats.get(i) + j)
                        .status(1)
                        .typeSeat(TypeSeat.STANDARD)
                        .build();
                seatRepository.save(seat);
            }
        }

        if(isDoubleSeat){
            int row = numberRow + 1;
           for(int i = 1 ; i <= numberCol ; i++){
               Seat seat = Seat.builder()
                       .screenRoom(screenRoomRepository.findById(idRoom).orElseThrow(() -> new NoSuchElementException("Not found screen room")))
                       .seatName(nameRowSeats.get(row) + i)
                       .status(1)
                       .typeSeat(TypeSeat.DOUBLE)
                       .build();
               seatRepository.save(seat);
           }

        }
    }

    public void deleteSeatForScreenRoom(long idRoom){
        seatRepository.deleteSeatByScreenRoomId(idRoom);
    }

    @Override
    public ScreenRoomResponse update(long id, ScreenRoomRequest screenRoomRequest) {
        ScreenRoomResponse screenRoomOld = findById(id);
        boolean checkScreenNameExist = screenRoomRepository.existsByScreenName(screenRoomRequest.getScreenName(),screenRoomRequest.getTheaterId());
        if(checkScreenNameExist && screenRoomOld.getScreenName().equals(screenRoomRequest.getScreenName())){
            checkScreenNameExist = false ;
        }

        if(checkScreenNameExist){
            return  null ;
        }else {
            ScreenRoom screenRoom = convertToScreenRoom(screenRoomRequest);
            screenRoom.setId(screenRoomOld.getId());
            if(screenRoomOld.isDoubleSeat() != screenRoomRequest.getIsDoubleSeat() || screenRoomOld.getNumberColSeat() != screenRoomRequest.getNumberColSeat() || screenRoomOld.getNumberRowSeat() != screenRoomRequest.getNumberRowSeat()){
                deleteSeatForScreenRoom(screenRoom.getId());
                addSeatForScreenRoom(screenRoom.getId(),screenRoom.getNumberRowSeat(),screenRoom.getNumberColSeat(),screenRoom.isDoubleSeat());
            }
            return convertToScreenRoomResponse(screenRoomRepository.save(screenRoom));
        }
    }

    @Override
    public ScreenRoomResponse findById(long id) {
        ScreenRoom screenRoom =  screenRoomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found screen room"));
        return convertToScreenRoomResponse(screenRoom);
    }

    @Override
    public void deleteById(long id) {
        ScreenRoomResponse screenRoom = findById(id);
        deleteSeatForScreenRoom(id);
        screenRoomRepository.deleteById(id);

    }

    boolean checkAddScreenRoom(long theaterId){
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(() -> new NoSuchElementException("Not found theater"));
        List<ScreenRoom> screenRoomList = screenRoomRepository.getScreenRoomByTheaterId(theaterId);
        return screenRoomList.size() < theater.getNumberOfScreens();
    }

    @Override
    public ScreenRoom convertToScreenRoom(ScreenRoomRequest screenRoomRequest) {
        return ScreenRoom.builder()
                .theater(theaterRepository.findById(screenRoomRequest.getTheaterId()).orElseThrow(() -> new NoSuchElementException("Not found theater")))
                .screenName(screenRoomRequest.getScreenName())
                .numberColSeat(screenRoomRequest.getNumberColSeat())
                .numberRowSeat(screenRoomRequest.getNumberRowSeat())
                .isDoubleSeat(screenRoomRequest.getIsDoubleSeat())
                .build();
    }

    @Override
    public ScreenRoomResponse convertToScreenRoomResponse(ScreenRoom screenRoom) {
       return ScreenRoomResponse.builder()
                .id(screenRoom.getId())
               .numberColSeat(screenRoom.getNumberColSeat())
               .numberRowSeat(screenRoom.getNumberRowSeat())
               .isDoubleSeat(screenRoom.isDoubleSeat())
                .screenName(screenRoom.getScreenName())
                .theaterName(screenRoom.getTheater().getName())
                .build();
    }

    @Override
    public List<ScreenRoom> getScreenByTheater(long theaterId) {
        return screenRoomRepository.getScreenRoomByTheaterId(theaterId);
    }

    @Override
    public List<ScreenRoom> getAll() {
        return screenRoomRepository.findAll();
    }

}
