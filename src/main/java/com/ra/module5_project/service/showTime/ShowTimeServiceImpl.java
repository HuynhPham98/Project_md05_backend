package com.ra.module5_project.service.showTime;

import com.ra.module5_project.model.constant.TypeMovie;
import com.ra.module5_project.model.dto.showTime.request.ShowTimeRequest;
import com.ra.module5_project.model.dto.showTime.request.ShowTimeRequestUpdate;
import com.ra.module5_project.model.dto.showTime.response.ShowTimePagination;
import com.ra.module5_project.model.entity.ShowTime;
import com.ra.module5_project.repository.ScreenRoomRepository;
import com.ra.module5_project.repository.ShowTimeRepository;
import com.ra.module5_project.service.movie.MovieService;
import com.ra.module5_project.service.screenRoom.ScreenRoomService;
import com.ra.module5_project.service.theater.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShowTimeServiceImpl implements ShowTimeService{
    @Autowired
    private ShowTimeRepository showTimeRepository ;
    @Autowired
    private MovieService movieService ;
    @Autowired
    private TheaterService theaterService ;
    @Autowired
    private ScreenRoomRepository screenRoomRepository ;
    @Override
    public ShowTimePagination findAllAndSearch(Pageable pageable,Long movieId ,Long theaterId , Long screenRoomId , Long showTimeId ) {
        LocalDate localDate = LocalDate.now();
        Page<ShowTime> page =  screenRoomRepository.findAllAndSearchAndPagination(pageable,movieId,theaterId,screenRoomId,localDate);


        return ShowTimePagination.builder()
                .showTimes(page.getContent())
                .currentPage(page.getNumber())
                .size(page.getSize())
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public ShowTime save(ShowTimeRequest showTimeRequest) {
        LocalDateTime showDate = null;
        try {
            showDate = LocalDateTime.parse(showTimeRequest.getShowTime());
        }catch (Exception e){
            throw new RuntimeException("Show time invalid");
        }
        boolean checkShowTimeExist = showTimeRepository.checkShowTimeExist(showDate,showTimeRequest.getTheaterId(),showTimeRequest.getScreenRoomId());
        if(checkShowTimeExist){
            return null ;
        }else {
            ShowTime showTime = convertDTOToShowTime(showTimeRequest);
            return showTimeRepository.save(showTime);
        }
    }

    @Override
    public ShowTime update(long idShowTime, ShowTimeRequestUpdate showTimeRequestUpdate) {
        ShowTime oldShowTime = findById(idShowTime);
        LocalDateTime showDate = null;
        try {
            showDate = LocalDateTime.parse(showTimeRequestUpdate.getShowTime());
        }catch (Exception e){
            throw new RuntimeException("Show time invalid");
        }
        boolean checkShowTimeExist = showTimeRepository.checkShowTimeExist(showDate,showTimeRequestUpdate.getTheaterId(),showTimeRequestUpdate.getScreenRoomId());
        LocalDateTime oldTime = oldShowTime.getShowTime().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime newTime = showDate.withMinute(0).withSecond(0).withNano(0);
        if(checkShowTimeExist && oldTime.equals( newTime)){
            checkShowTimeExist = false ;
        }
        if(checkShowTimeExist){
            return null ;
        }else {
            TypeMovie typeMovie = null ;
            try {
                typeMovie = TypeMovie.valueOf(showTimeRequestUpdate.getTypeMovie().toUpperCase());
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Invalid type seat: " + showTimeRequestUpdate.getTypeMovie());
            }
            ShowTime rs = ShowTime.builder()
                    .id(oldShowTime.getId())
                    .movie(movieService.findById(showTimeRequestUpdate.getMovieId()))
                    .screenRoom(screenRoomRepository.findById(showTimeRequestUpdate.getScreenRoomId()).orElseThrow(() -> new NoSuchElementException("Not found screen room")))
                    .showDate(showDate.toLocalDate())
                    .showTime(showDate)
                    .theater(theaterService.findById(showTimeRequestUpdate.getTheaterId()))
                    .typeMovie(typeMovie)
                    .created_date(LocalDateTime.now())
                    .build();
            return showTimeRepository.save(rs);
        }
    }

    @Override
    public ShowTime findById(long id) {
        return showTimeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found show time"));
    }

    @Override
    public void deleteById(long id) {
        ShowTime showTime = findById(id);
        showTimeRepository.deleteById(id);
    }

    @Override
    public ShowTime convertDTOToShowTime(ShowTimeRequest showTimeRequest) {
        LocalDateTime showTime = null;
        try {
            showTime = LocalDateTime.parse(showTimeRequest.getShowTime());
        }catch (Exception e){
            throw new RuntimeException("Show time invalid");
        }

        TypeMovie typeMovie = null ;
        try {
            typeMovie = TypeMovie.valueOf(showTimeRequest.getTypeMovie().toUpperCase());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid type seat: " + showTimeRequest.getTypeMovie());
        }
        return ShowTime.builder()
                .movie(movieService.findById(showTimeRequest.getMovieId()))
                .screenRoom(screenRoomRepository.findById(showTimeRequest.getScreenRoomId()).orElseThrow(() -> new NoSuchElementException("Not found screen room")))
                .showDate(showTime.toLocalDate())
                .showTime(showTime)
                .typeMovie(typeMovie)
                .theater(theaterService.findById(showTimeRequest.getTheaterId()))
                .created_date(LocalDateTime.now())
                .build();
    }

    @Override
    public List<ShowTime> getShowTimeByMovieAndDate(long movieId, LocalDate date,String theaterName) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return showTimeRepository.getShowTimeByMovieAndDate(movieId,date,theaterName).stream().filter(showTime -> !showTime.getShowTime().isBefore(localDateTime)).toList();
    }

    @Override
    public List<ShowTime> getShowTimeByScreenRoom(long screenRoomId) {
        return showTimeRepository.getShowTimesByScreenRoomId(screenRoomId);
    }
}
