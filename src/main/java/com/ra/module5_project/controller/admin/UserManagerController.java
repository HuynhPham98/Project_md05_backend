package com.ra.module5_project.controller.admin;

import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.user.request.UserRegisterRequest;
import com.ra.module5_project.model.dto.user.request.UserUpdateDto;
import com.ra.module5_project.model.dto.user.response.UserPaginationAdmin;
import com.ra.module5_project.model.dto.user.response.UserResponse;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myService.com/v1/admin/user")
@RequiredArgsConstructor

public class UserManagerController {
    @Autowired
    private final UserService userService;

    // 1. Lấy danh sách người dùng với phân trang và tìm kiếm
    @GetMapping
    public ResponseEntity<UserPaginationAdmin> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "search", defaultValue = "") String search) {
        Pageable pageable = PageRequest.of(page, size);
        UserPaginationAdmin users = userService.findAllAndSearch(pageable, search);
        return ResponseEntity.ok(users);
    }

    // 2. Cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable long id,
            @ModelAttribute UserUpdateDto userUpdateDto) {
        // Kiểm tra xem người dùng có tồn tại không
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy người dùng
        }

        // Cập nhật người dùng
        UserResponse updatedUser = userService.updateUser(user, userUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }

    // 3. Đổi trạng thái (Block/Open) người dùng
    @PutMapping("/{userId}/toggle-status")
    public ResponseEntity<String> toggleUserStatus(@PathVariable long userId) {
        boolean result = userService.blockOrOpen(userId);

        if (result) {
            return ResponseEntity.ok("Trạng thái người dùng đã được thay đổi thành công.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền thay đổi trạng thái tài khoản ADMIN.");
        }
    }

    // 4. Lấy thông tin chi tiết người dùng
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        User user = userService.findById(id);
        UserResponse userResponse = userService.userToUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
