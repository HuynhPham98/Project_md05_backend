package com.ra.module5_project.service.comboFood;

import com.ra.module5_project.model.dto.comboFoot.request.ComboFoodRequest;
import com.ra.module5_project.model.dto.comboFoot.request.ComboFoodUpdate;
import com.ra.module5_project.model.dto.comboFoot.response.ComboFoodPagination;
import com.ra.module5_project.model.entity.ComboFood;
import com.ra.module5_project.repository.ComboFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ComboFoodServiceImpl implements ComboFoodService{
    @Autowired
    private ComboFoodRepository comboFoodRepository ;

    @Override
    public ComboFoodPagination findAll(Pageable pageable) {
        Page<ComboFood> page = comboFoodRepository.findAll(pageable);
        return ComboFoodPagination.builder()
                .comboFoods(page.getContent())
                .currentPage(page.getNumber())
                .size(page.getSize())
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public ComboFood save(ComboFoodRequest comboFoodRequest) {
        return comboFoodRepository.save(convertRequest(comboFoodRequest));
    }

    @Override
    public ComboFood update(long id ,ComboFoodUpdate comboFoodUpdate) {
        ComboFood oldComboFood = findById(id);
        boolean checkNameExist = comboFoodRepository.checkNameExist(comboFoodUpdate.getName());
        if(checkNameExist && comboFoodUpdate.getName().equalsIgnoreCase(oldComboFood.getName())){
            checkNameExist = false ;
        }
        if(checkNameExist){
            return null;
        }else {
            ComboFood newComboFood = convertUpdate(comboFoodUpdate);
            newComboFood.setId(oldComboFood.getId());
            return comboFoodRepository.save(newComboFood);
        }

    }

    @Override
    public ComboFood findById(long id) {
        return comboFoodRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found combo food"));
    }

    @Override
    public void deleteById(long id) {
        ComboFood comboFood = findById(id);
        comboFoodRepository.deleteById(id);
    }

    @Override
    public ComboFood convertRequest(ComboFoodRequest comboFoodRequest) {
        return ComboFood.builder()
                .name(comboFoodRequest.getName())
                .description(comboFoodRequest.getDescription())
                .image(comboFoodRequest.getImage())
                .price(comboFoodRequest.getPrice())
                .build();
    }

    @Override
    public ComboFood convertUpdate(ComboFoodUpdate comboFoodUpdate) {
        return ComboFood.builder()
                .name(comboFoodUpdate.getName())
                .description(comboFoodUpdate.getDescription())
                .image(comboFoodUpdate.getImage())
                .price(comboFoodUpdate.getPrice())
                .build();
    }

    @Override
    public ComboFood updateImage(ComboFood comboFood) {
        return comboFoodRepository.save(comboFood);
    }

    @Override
    public List<ComboFood> userFindAll() {
        return comboFoodRepository.findAll();
    }


}
