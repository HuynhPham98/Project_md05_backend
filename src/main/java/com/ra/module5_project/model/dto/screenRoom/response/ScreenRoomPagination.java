package com.ra.module5_project.model.dto.screenRoom.response;

import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.model.entity.ScreenRoom;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScreenRoomPagination {
    private List<ScreenRoom> screenRooms ;
    private int totalPage;
    private int size ;
    private int currentPage ;
    private long totalElement ;
}
