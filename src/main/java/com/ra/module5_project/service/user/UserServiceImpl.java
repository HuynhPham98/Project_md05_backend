package com.ra.module5_project.service.user;

import com.ra.module5_project.model.dto.user.request.UserRegisterRequest;
import com.ra.module5_project.model.dto.user.request.UserUpdateDto;
import com.ra.module5_project.model.dto.user.request.UserUpdatePasswordDto;
import com.ra.module5_project.model.dto.user.response.UserPaginationAdmin;
import com.ra.module5_project.model.dto.user.response.UserResponse;
import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.Role;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.repository.BookingRepository;
import com.ra.module5_project.repository.UserRepository;
//import com.ra.module5_project.service.product.ProductService;
import com.ra.module5_project.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BookingRepository bookingRepository;

    private final UserRepository userRepository;

    private final RoleService roleService;
//    private final ProductService productService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
//        this.productService = productService;
    }

    @Override
    public UserPaginationAdmin findAllAndSearch(Pageable pageable,String search) {
        Page<User> page ;
        if(search != null){
            page = userRepository.findAllAndSearch(pageable,search);
        }else {
            page = userRepository.findAll(pageable) ;
        }
        return UserPaginationAdmin.builder()
                .users(page.getContent().stream().map(this::userToUserResponse).toList())
                .currentPage(page.getNumber())
                .totalElement(page.getTotalElements())
                .size(page.getSize())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .status(user.isStatus())
                .avatar(user.getAvatar())
                .roles(user.getRoles())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }

    @Override
    public User save(UserRegisterRequest userRegisterRequest) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName("USER"));
        User user = User.builder()
                .roles(roles)
                .username(userRegisterRequest.getUsername())
                .password(new BCryptPasswordEncoder().encode(userRegisterRequest.getPassword()))
                .status(true)
                .avatar("https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/2048px-User-avatar.svg.png")
                .build();
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public String saveImage(MultipartFile file) {
        if (file == null) {
            throw new IllegalArgumentException("Tệp không được null");
        }

        if (file.getSize() > 0) {
            String uploadDir = "C:\\Users\\dell\\IdeaProjects\\backend_huong\\src\\main\\resources\\uploads\\";
            String filePath = uploadDir + file.getOriginalFilename();
            File dir = new File(uploadDir);

            // Tạo thư mục nếu chưa tồn tại
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File fl = new File(filePath);
            // Chuyển tệp vào thư mục
            try {
                file.transferTo(fl);
            } catch (IOException e) {
                throw new RuntimeException("Có lỗi xảy ra khi tải lên tệp: " + e.getMessage(), e);
            }

            return file.getOriginalFilename();
        } else {
            throw new IllegalArgumentException("Kích thước tệp phải lớn hơn 0");
        }
    }

    @Override
    public UserResponse updateUser(User user, UserUpdateDto userUpdateDto) {
        user.setFullName(userUpdateDto.getFullName());
        user.setUpdated_at(new Date());
//        user.setPassword(new BCryptPasswordEncoder().encode(userUpdateDto.getPassword()));
        user.setPhone(userUpdateDto.getPhone());
        user.setAddress(userUpdateDto.getAddress());
        user.setAvatar(userUpdateDto.getImage());
        User newUser = userRepository.save(user);
        return userToUserResponse(newUser);
    }

    @Override
    public User save(long id, UserRegisterRequest userRegisterRequest) {
        User userOld = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found user"));
        User user = User.builder()
                .roles(userOld.getRoles())
                .username(userRegisterRequest.getUsername())
                .password(new BCryptPasswordEncoder().encode(userRegisterRequest.getPassword()))
                .status(userOld.isStatus())
                .id(userOld.getId())
                .build();
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found user"));
    }

    @Override
    public boolean checkUsernameExist(String username) {
        return userRepository.findAll().stream().map(User::getUsername).anyMatch(name -> name.equals(username));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public boolean changePassword(User user, UserUpdatePasswordDto userUpdatePasswordDto) {
        if (new BCryptPasswordEncoder().matches(userUpdatePasswordDto.getOldPass(), user.getPassword())) {
            if (userUpdatePasswordDto.getNewPass().equals(userUpdatePasswordDto.getConfirmNewPass())) {
                user.setPassword(new BCryptPasswordEncoder().encode(userUpdatePasswordDto.getNewPass()));
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void logout(User user) {
        user.setStatusLogin(false);
        userRepository.save(user);
    }

    @Override
    public boolean blockOrOpen(long userId) {
        User user = findById(userId);
        boolean checkAdmin = user.getRoles().stream().map(Role::getRoleName).anyMatch(roleName -> roleName.equals("ADMIN")) ;
        if(!checkAdmin){
            if(user != null){
                user.setStatus(!user.isStatus());
                userRepository.save(user);
                return true;
            }else {
                return false ;
            }
        }else {
            return false;
        }
    }


    @Override
    public List<UserResponse> newAccountsThisMonth() {
        Date date = new Date();

        // Sử dụng Calendar để lấy ngày đầu và cuối tháng
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Lấy ngày đầu tiên của tháng
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // Lấy ngày cuối cùng của tháng
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();
        return userRepository.newAccountsThisMonth(startDate, endDate).stream().map(this::userToUserResponse).toList();
    }

    @Override
    public User updateGetCoin(User user) {
        if(!user.isGetCoin()){
            user.setGetCoin(true);
            user.setCoin(user.getCoin() + 1000);
            return userRepository.save(user);
        }else {
            return null ;
        }
    }

    @Override
    public void useCoin(User user) {
        user.setCoin(0);
        userRepository.save(user);
    }
}
