package com.ra.module5_project.service.screenRoom;

import com.ra.module5_project.model.dto.screenRoom.request.ScreenRoomRequest;
import com.ra.module5_project.model.dto.screenRoom.response.ScreenRoomPagination;
import com.ra.module5_project.model.dto.screenRoom.response.ScreenRoomResponse;
import com.ra.module5_project.model.entity.ScreenRoom;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScreenRoomService {
    ScreenRoomPagination findAllAndSearch(Pageable pageable , String search);
    ScreenRoomResponse save(ScreenRoomRequest screenRoomRequest);
    ScreenRoomResponse update(long id , ScreenRoomRequest screenRoomRequest);
    ScreenRoomResponse findById(long id);
    void deleteById(long id);
    ScreenRoom convertToScreenRoom(ScreenRoomRequest screenRoomRequest);
    ScreenRoomResponse convertToScreenRoomResponse(ScreenRoom screenRoom);
    List<ScreenRoom> getScreenByTheater(long theaterId);
    List<ScreenRoom> getAll();
}
