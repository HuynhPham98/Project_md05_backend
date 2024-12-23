package com.ra.module5_project.service.foodBooking;

import com.ra.module5_project.model.entity.FoodBooking;
import com.ra.module5_project.repository.FoodBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FoodBookingServiceImpl implements FoodBookingService {
    @Autowired
    private FoodBookingRepository foodBookingRepository ;

    @Override
    public List<FoodBooking> findAll() {
        return foodBookingRepository.findAll();
    }

    @Override
    public FoodBooking save(FoodBooking foodBooking) {
        return foodBookingRepository.save(foodBooking);
    }

    @Override
    public FoodBooking findById(long foodBookingId) {
        return foodBookingRepository.findById(foodBookingId).orElseThrow(() -> new NoSuchElementException("Not found combo food"));
    }

    @Override
    public void deleteById(long id) {
        FoodBooking foodBooking = findById(id);
        foodBookingRepository.deleteById(id);
    }

    @Override
    public List<FoodBooking> getFoodByBookingId(long bookingId) {
        return foodBookingRepository.getComboFoodByBookingId(bookingId);
    }
}
