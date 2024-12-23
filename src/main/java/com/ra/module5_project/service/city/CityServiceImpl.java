package com.ra.module5_project.service.city;

import com.ra.module5_project.model.dto.city.request.CityRequest;
import com.ra.module5_project.model.dto.city.request.CityRequestUpdate;
import com.ra.module5_project.model.dto.city.response.CityPagination;
import com.ra.module5_project.model.entity.City;
import com.ra.module5_project.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityRepository cityRepository ;

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public CityPagination findAll(Pageable pageable, String search) {
        Page<City> page = null ;
        if(search != null){
            page = cityRepository.findAllAndSearchName(pageable,search);
        }else {
            page = cityRepository.findAll(pageable);
        }
        return CityPagination.builder()
                .cities(page.getContent())
                .size(page.getSize())
                .currentPage(page.getNumber())
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }



    @Override
    public City addCity(CityRequest cityRequest) {
        return cityRepository.save(convertDtoToCity(cityRequest));
    }

    public City convertDtoToCity(CityRequest cityRequest){
        return City.builder()
                .cityName(cityRequest.getCityName())
                .build();
    }

    @Override
    public City updateCity(long id ,CityRequestUpdate cityRequestUpdate) {
        City oldCity = findById(id);
       boolean checkNameExist = cityRepository.existsByCityName(cityRequestUpdate.getCityName());
       if(checkNameExist && oldCity.getCityName().equals(cityRequestUpdate.getCityName())){
            checkNameExist = false ;
       }

       if(checkNameExist){
           return null ;
       }else {
           oldCity.setCityName(cityRequestUpdate.getCityName());
           return cityRepository.save(oldCity);
       }
    }

    @Override
    public City findById(long id) {
        return cityRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found city"));
    }

    @Override
    public void deleteById(long id) {
        City city = findById(id);
       cityRepository.deleteById(id);
    }

}
