package com.ra.module5_project.controller.admin;

import com.ra.module5_project.model.dto.user.response.UserResponse;
import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.Movie;
import com.ra.module5_project.model.entity.ScreenRoom;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.service.booking.BookingService;
import com.ra.module5_project.service.movie.MovieService;
import com.ra.module5_project.service.screenRoom.ScreenRoomService;
import com.ra.module5_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api.myService.com/v1/admin/dashboard")
public class DashboardController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreenRoomService screenRoomService;

    //Thống kê users đăng ký mới nhất
    @GetMapping("/new_user_register")
    public ResponseEntity<?> countNewRegister() {
        List<UserResponse> userResponses = userService.newAccountsThisMonth();
        return ResponseEntity.ok(userResponses);
    }

    //Thong ke tong so phong chieu
    @GetMapping("/count_screen_room")
    public ResponseEntity<?> countScreenRoom() {
        List<ScreenRoom> screenRooms = screenRoomService.getAll();
        return ResponseEntity.ok(screenRooms);
    }

    //Thống kê doanh thu hàng tháng theo năm
    @GetMapping("/revenue/{year}")
    public ResponseEntity<?> countRevenue(@PathVariable("year") int year) {
        Map<Integer, Double> revenue = bookingService.getRevenueByYear(year);
        return ResponseEntity.ok(revenue);
    }

    //Thống kê tổng số phim sắp chiếu
    @GetMapping("/movies_coming_soon")
    public ResponseEntity<?> countMoviesComingSoon() {
        List<Movie> movies = movieService.findAllMoviesInFuture();
        return ResponseEntity.ok(movies);
    }

    //Thống kê thông tin người dùng có số tiền mua nhiều nhất
    @GetMapping("/top_1_user_revenue")
    public ResponseEntity<?> top1UserRevenue() {
        List<Booking> bookings = bookingService.findAllBookingByTopUser();
        return ResponseEntity.ok(bookings);
    }

    //Thong ke tong doanh thu
    @GetMapping("/revenue/total_price")
    public ResponseEntity<?> totalRevenue() {
        Double totalPrice = bookingService.totalPrice();
        return ResponseEntity.ok(totalPrice);
    }
}
