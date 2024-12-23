package com.ra.module5_project.service.booking;

import com.ra.module5_project.model.constant.TypeSeat;
import com.ra.module5_project.model.dto.booking.request.BookingRequest;
import com.ra.module5_project.model.dto.booking.response.BookingPagination;
import com.ra.module5_project.model.dto.booking.response.BookingResponse;
import com.ra.module5_project.model.dto.foodBooking.request.FoodBookingRequest;
import com.ra.module5_project.model.entity.*;
import com.ra.module5_project.repository.BookingRepository;
import com.ra.module5_project.repository.BookingSeatRepository;
import com.ra.module5_project.repository.SeatRepository;
import com.ra.module5_project.repository.TicketPriceRepository;
import com.ra.module5_project.security.principle.UserPrinciple;
import com.ra.module5_project.service.comboFood.ComboFoodService;
import com.ra.module5_project.service.foodBooking.FoodBookingService;
import com.ra.module5_project.service.giftService.GiftService;
import com.ra.module5_project.service.showTime.ShowTimeService;
import com.ra.module5_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository ;
    @Autowired
    private ShowTimeService showTimeService ;
    @Autowired
    private UserService userService ;
    @Autowired
    private SeatRepository seatRepository ;
    @Autowired
    private TicketPriceRepository ticketPriceRepository ;
    @Autowired
    private BookingSeatRepository bookingSeatRepository ;
    @Autowired
    private FoodBookingService foodBookingService ;
    @Autowired
    private ComboFoodService comboFoodService ;
    @Autowired
    private GiftService giftService ;

    @Override
    public BookingPagination findAll(Pageable pageable,LocalDate date ,Long movieId , Long theaterId , Long screenRoomId , Long showTimeId) {

        Page<Booking> page =  bookingRepository.findAllAndSearchAndPagination(pageable,date,movieId,theaterId,screenRoomId,showTimeId);
        return BookingPagination.builder()
                .bookings(page.getContent())
                .currentPage(page.getNumber())
                .size(page.getSize())
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public Booking save(BookingRequest bookingRequest , UserPrinciple userPrinciple) {
        boolean checkSeatExist = false ;
        for(long id : bookingRequest.getListSeatId()){
            if(bookingSeatRepository.checkSeatExist(bookingRequest.getShowTimeId(),id)){
                checkSeatExist = true ;
                break;
            }
        }
        if(checkSeatExist){
            return null ;
        }else {
            Gift gift = null ;
            if(bookingRequest.getGiftId() != 0){
                gift = giftService.findById(bookingRequest.getGiftId());
            }
            double totalPriceMovie = getPriceOfSeat(showTimeService.findById(bookingRequest.getShowTimeId()),bookingRequest);
            Booking booking = Booking.builder()
                    .showTime(showTimeService.findById(bookingRequest.getShowTimeId()))
                    .user(userPrinciple.getUser())
                    .totalSeat(bookingRequest.getListSeatId().size())
                    .totalPriceMovie(totalPriceMovie)
                    .totalPriceFood(bookingRequest.getTotalPriceFood())
                    .status(true)
                    .gift(gift)
                    .discount(bookingRequest.getDiscount())
                    .created_at(LocalDate.now())
                    .build();
            Booking newBooking = bookingRepository.save(booking) ;
            List<Seat> seats = bookingRequest.getListSeatId().stream().map(id -> {
                return seatRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found seat"));
            }).toList();
            seats.forEach(seat -> {
                BookingSeat bookingSeat = BookingSeat.builder()
                        .booking(newBooking)
                        .seat(seat)
                        .quantity(seat.getTypeSeat() == TypeSeat.STANDARD ? 1 : 2)
                        .price(convertSeatToPrice(seat,showTimeService.findById(bookingRequest.getShowTimeId())))
                        .created_date(LocalDateTime.now())
                        .build();

                bookingSeatRepository.save(bookingSeat);
            });

            if(!bookingRequest.getFoodBookingRequests().isEmpty()){
                for(FoodBookingRequest food : bookingRequest.getFoodBookingRequests()){
                    ComboFood comboFood = comboFoodService.findById(food.getComboFoodId());
                    FoodBooking newFood = FoodBooking.builder()
                            .booking(newBooking)
                            .comboFood(comboFood)
                            .quantity(food.getQuantity())
                            .price(comboFood.getPrice())
                            .build();

                    foodBookingService.save(newFood);
                }
            }
            return newBooking ;
        }

    }

    double convertSeatToPrice(Seat seat,ShowTime showTime){
        LocalTime startTime = null ;
        LocalTime endTime  = null ;
        if(LocalTime.now().isAfter(LocalTime.of(10,0)) && LocalTime.now().isBefore(LocalTime.of(21,59,59))){
            startTime = LocalTime.of(10,0,0);
            endTime = LocalTime.of(21,59,59);
        }else {
            startTime = LocalTime.of(22,0,0);
            endTime = LocalTime.of(9,59 ,59);
        }
        LocalTime finalStartTime = startTime;
        LocalTime finalEndTime = endTime;
        int dayOfWeek = 0 ;
        if(LocalDate.now().getDayOfWeek().getValue() > 5 && LocalDate.now().getDayOfWeek().getValue() <= 7){
            dayOfWeek = 78 ;
        }else {
            dayOfWeek = 23456 ;
        }
        int finalDayOfWeek = dayOfWeek;
        List<Holiday> holidays = new ArrayList<>();
        holidays.add(Holiday.builder().month(1).day(1).build());
        holidays.add(Holiday.builder().month(4).day(30).build());
        holidays.add(Holiday.builder().month(5).day(1).build());
        holidays.add(Holiday.builder().month(9).day(2).build());
        holidays.add(Holiday.builder().month(12).day(24).build());
        boolean isHoliday = holidays.contains(Holiday.builder().month(LocalDate.now().getMonthValue()).day(LocalDate.now().getDayOfMonth()).build());
        TicketPrice ticketPrice = TicketPrice.builder()
                .typeSeat(seat.getTypeSeat().toString().toLowerCase()).typeMovie(showTime.getTypeMovie().toString())
                .startTime(finalStartTime)
                .endTime(finalEndTime)
                .dayOfWeeks(finalDayOfWeek)
                .isHoliday(isHoliday)
                .price(0)
                .build();
        TicketPrice dataTicket = ticketPriceRepository.findTicketPrice(ticketPrice.getTypeSeat(),ticketPrice.getTypeMovie(),ticketPrice.getStartTime(),ticketPrice.getEndTime(), ticketPrice.getDayOfWeeks(), ticketPrice.isHoliday());
        if(dataTicket != null){
            return dataTicket.getPrice();
        }else {
            return 0 ;
        }
    }
    @Override
    public Double convertSeatToMoney(Long showTimeId,List<Long> seatIds) {
        ShowTime showTime = showTimeService.findById(showTimeId);
        BookingRequest bookingRequest = BookingRequest.builder()
                .showTimeId(showTimeId)
                .listSeatId(seatIds)
                .totalPriceFood(0)
                .build();

        return  getPriceOfSeat(showTime,bookingRequest);
    }

    @Override
    public Double seatToMoney(long showTimeId, long seatId) {
        ShowTime showTime =  showTimeService.findById(showTimeId);
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new NoSuchElementException("Not found seat"));
        return convertSeatToPrice(seat,showTime);
    }

    @Override
    public BookingResponse convertToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .showTimeId(booking.getShowTime().getId())
                .userId(booking.getUser().getId())
                .totalSeat(booking.getTotalSeat())
                .totalPriceMovie(booking.getTotalPriceMovie())
                .totalPriceFood(booking.getTotalPriceFood())
                .status(booking.isStatus())
                .created_at(booking.getCreated_at())
                .build();
    }


    public double getPriceOfSeat(ShowTime showTime , BookingRequest bookingRequest){
        List<Seat> seats = bookingRequest.getListSeatId().stream().map(id -> {
            return seatRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found seat"));
        }).toList();
        return seats.stream().map(seat -> convertSeatToPrice(seat,showTime)).reduce(0.0, Double::sum);

    }

    @Override
    public Booking findById(long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new NoSuchElementException("Not found booking"));
    }

    @Override
    public List<Booking> findAllByUserId(long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    @Override
    public Map<Integer, Double> getRevenueByYear ( int year){
        List<Object[]> results = bookingRepository.revenueByYear(year);
        Map<Integer, Double> revenueByMonth = new HashMap<>();
        for (Object[] result : results) {
            Integer mont = (Integer) result[0];
            Double revenue = (Double) result[1];
            revenueByMonth.put(mont, revenue);
        }

        for (int i = 1; i <= 12; i++) {
            revenueByMonth.putIfAbsent(i, 0.0);
        }
        return revenueByMonth;
    }

    @Override
    public Double totalPrice () {
        return bookingRepository.totalPrice();
    }

    @Override
    public List<Booking> findAllBookingByTopUser () {
        User topUser = bookingRepository.findTopUserByRevenue();
        if (topUser != null) {
            try {
                return bookingRepository.findAllByUserId(topUser.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Bước 3: Lấy danh sách tất cả Booking của User đó
        }
        return null;
    }
}