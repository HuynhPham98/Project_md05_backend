package com.ra.module5_project.service.giftUser;

import com.ra.module5_project.model.dto.GiftUser.request.GiftUserRequest;
import com.ra.module5_project.model.entity.GiftUser;
import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.repository.GiftRepository;
import com.ra.module5_project.repository.GiftUserRepository;
import com.ra.module5_project.service.giftService.GiftService;
import com.ra.module5_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GiftUserServiceImpl implements  GiftUserService{
    @Autowired
    private GiftUserRepository giftUserRepository ;
    @Autowired
    private GiftService giftService ;
    @Autowired
    private UserService userService ;

    @Override
    public List<GiftUser> findGiftByUser(long userId) {
        return giftUserRepository.findAll();
    }

    @Override
    public GiftUser save(GiftUserRequest giftUserRequest) {
        LocalDate now = LocalDate.now();
       boolean checkSave = giftUserRepository.checkReceiveGift(giftUserRequest.getUserId(), now);
       if(checkSave){
           return null ;
       }else {
           GiftUser giftUser = convertRequest(giftUserRequest);
           return giftUserRepository.save(giftUser);
       }
    }

    @Override
    public GiftUser update(GiftUserRequest giftUserRequest) {
        GiftUser giftUser = convertRequest(giftUserRequest);
        return giftUserRepository.save(giftUser);
    }

    @Override
    public GiftUser findById(long id) {
        return giftUserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found gift by user"));
    }

    @Override
    public void deleteById(long id) {
        GiftUser giftUser = findById(id);
        giftUserRepository.deleteById(id);
    }

    @Override
    public boolean checkReceiveGift(long userId) {
        LocalDate now = LocalDate.now();
       return giftUserRepository.checkReceiveGift(userId,now);
    }

    @Override
    public GiftUser convertRequest(GiftUserRequest giftUserRequest) {
        return GiftUser.builder()
                .gift(giftService.findById(giftUserRequest.getGiftId()))
                .user(userService.findById(giftUserRequest.getUserId()))
                .status(giftUserRequest.isStatus())
                .build();
    }

    @Override
    public GiftUser useGift(long idGiftUser) {
        GiftUser giftUser = findById(idGiftUser);
        giftUser.setStatus(false);
        return giftUserRepository.save(giftUser);
    }

    @Override
    public List<GiftUser> getGiftUser(long userId) {
        return giftUserRepository.getGiftUserByUserId(userId);
    }


}
