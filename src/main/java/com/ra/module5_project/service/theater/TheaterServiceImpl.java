package com.ra.module5_project.service.theater;

import com.ra.module5_project.model.dto.theater.request.TheaterRequest;
import com.ra.module5_project.model.dto.theater.request.TheaterRequestUpdate;
import com.ra.module5_project.model.dto.theater.response.TheaterPagination;
import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.model.entity.Theater;
import com.ra.module5_project.repository.CityRepository;
import com.ra.module5_project.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TheaterServiceImpl implements TheaterService{
    @Autowired
    private TheaterRepository theaterRepository ;

    @Override
    public TheaterPagination findAllAndSearch(Pageable pageable, String search) {
        Page<Theater> page = null ;
        if(search != null){
            page = theaterRepository.findAllAndSearchName(pageable, search);
        }else {
            page = theaterRepository.findAll(pageable);
        }
        return TheaterPagination.builder()
                .theaters(page.getContent())
                .currentPage(page.getNumber())
                .totalElement(page.getTotalElements())
                .size(page.getSize())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public Theater save(TheaterRequest theaterRequest) {
        Theater theater = convertToTheater(theaterRequest);
        return theaterRepository.save(theater);
    }

    @Override
    public Theater update(long id , TheaterRequestUpdate theaterRequestUpdate) {
        Theater oldTheater = findById(id);
        boolean checkTheaterNameExist = theaterRepository.checkTheaterNameExist(theaterRequestUpdate.getName());
        if(checkTheaterNameExist && oldTheater.getName().equalsIgnoreCase(theaterRequestUpdate.getName())){
            checkTheaterNameExist = false ;
        }
        if(checkTheaterNameExist){
            return null ;
        }else {
            Theater theater = Theater.builder()
                    .id(oldTheater.getId())
                    .address(theaterRequestUpdate.getAddress())
                    .name(theaterRequestUpdate.getName())
                    .numberOfScreens(theaterRequestUpdate.getNumberOfScreens())
                    .phoneNumber(theaterRequestUpdate.getPhoneNumber())
                    .build();
            return theaterRepository.save(theater);
        }
    }

    @Override
    public void deleteById(long id) {
        Theater theater = findById(id);
        theaterRepository.deleteById(id);
    }

    @Override
    public Theater findById(long id) {
        return theaterRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found theater"));
    }

    @Override
    public boolean checkNameExist(String name) {
        return theaterRepository.checkTheaterNameExist(name);
    }

    @Override
    public List<Theater> getTheaters() {
        return theaterRepository.findAll();
    }

    @Override
    public List<Theater> getTheaterByCity(String cityName) {
       return theaterRepository.getTheaterByCity(cityName);
    }

    Theater convertToTheater(TheaterRequest theaterRequest){
        return Theater.builder()
                .address(theaterRequest.getAddress())
                .name(theaterRequest.getName())
                .numberOfScreens(theaterRequest.getNumberOfScreens())
                .phoneNumber(theaterRequest.getPhoneNumber())
                .build();
    }
}
