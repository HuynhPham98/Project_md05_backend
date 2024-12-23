package com.ra.module5_project.service.user;

import com.ra.module5_project.model.dto.user.request.UserRegisterRequest;
import com.ra.module5_project.model.dto.user.request.UserUpdateDto;
import com.ra.module5_project.model.dto.user.request.UserUpdatePasswordDto;
import com.ra.module5_project.model.dto.user.response.UserPaginationAdmin;
import com.ra.module5_project.model.dto.user.response.UserResponse;
import com.ra.module5_project.model.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserPaginationAdmin findAllAndSearch(Pageable pageable, String search);
    UserResponse userToUserResponse(User user);
    User save(UserRegisterRequest userRegisterRequest);
    User save(User user);
    UserResponse updateUser(User user , UserUpdateDto userUpdateDto);
    User save(long id ,UserRegisterRequest userRegisterRequest);
    void delete(long id);
    User findById(long id);
    boolean checkUsernameExist(String username);
    User getUserByUsername(String username);
    boolean changePassword(User user ,UserUpdatePasswordDto userUpdatePasswordDto);
    void logout(User user);
    boolean blockOrOpen(long userId);
    List<UserResponse> newAccountsThisMonth();
    User updateGetCoin(User user);
    void useCoin(User user);
}
