package com.ra.module5_project.service.bookingSeat;

import com.ra.module5_project.model.dto.bookingSeat.request.BookingSeatRequest;
import com.ra.module5_project.model.dto.bookingSeat.response.BookingSeatPagination;
import com.ra.module5_project.model.entity.BookingSeat;
import com.ra.module5_project.model.entity.Seat;
import com.ra.module5_project.repository.BookingSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingSeatServiceImpl implements BookingSeatService{
    @Autowired
    private BookingSeatRepository bookingSeatRepository ;
    @Override
    public List<BookingSeat> findAllByBookingId(long bookingId) {
        return bookingSeatRepository.findAllByBookingId(bookingId);
    }

    @Override
    public BookingSeat save(BookingSeat bookingSeat) {
        return bookingSeatRepository.save(bookingSeat);
    }

    @Override
    public BookingSeat findById(long id) {
        return bookingSeatRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found booking seat"));
    }

    @Override
    public List<Seat> getListSeatSold(long showTimeId) {
        return bookingSeatRepository.getBookingSeatsByShowTime(showTimeId).stream().map(BookingSeat::getSeat).toList();
    }

    @Override
    public void deleteByBookingId(long bookingId) {
        bookingSeatRepository.deleteByBookingId(bookingId);
    }

    @Override
    public List<BookingSeat> getBookingSeatByBookingId(long bookingId) {
        return bookingSeatRepository.getBookingSeatByBookingId(bookingId);
    }


}
