package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.GiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GiftUserRepository extends JpaRepository<GiftUser,Long> {
    @Query("select count(ug) > 0 from GiftUser ug where ug.gift.expiredDate >= :date and ug.user.id = :userId")
    boolean checkReceiveGift(@Param("userId") long userId , @Param("date")LocalDate date);

    @Query("select gu from GiftUser gu where gu.user.id = :userId and gu.status = true")
    List<GiftUser> getGiftUserByUserId(@Param("userId") long userId);
}
