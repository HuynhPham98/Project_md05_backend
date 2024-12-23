package com.ra.module5_project.service.giftUser;

import com.ra.module5_project.model.dto.GiftUser.request.GiftUserRequest;
import com.ra.module5_project.model.entity.GiftUser;
import com.ra.module5_project.model.entity.User;

import java.util.List;

public interface GiftUserService {
    List<GiftUser> findGiftByUser(long userId);
    GiftUser save(GiftUserRequest giftUserRequest);
    GiftUser update(GiftUserRequest giftUserRequest);
    GiftUser findById(long id);
    void deleteById(long id);
    boolean checkReceiveGift(long userId);
    GiftUser convertRequest(GiftUserRequest giftUserRequest);
    GiftUser useGift(long idGiftUser);
    List<GiftUser> getGiftUser(long userId);
}
