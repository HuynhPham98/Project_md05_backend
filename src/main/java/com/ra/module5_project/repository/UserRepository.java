package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.Booking;
import com.ra.module5_project.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByUsername(String username);

    boolean existsByUsername(String username);

    User findUserById(long id);
    User getUserByEmail(String email);

    @Query("select u from User u where u.username like %:_search% or u.email like %:_search% or u.phone like %:_search%")
    Page<User> findAllAndSearch(Pageable pageable , @Param("_search") String search);

    @Query("select u from User u where u.created_at between :startDate and :endDate")
    List<User> newAccountsThisMonth(Date startDate , Date endDate);

    @Modifying
    @Query("UPDATE User u SET u.getCoin = false WHERE u.getCoin = true")
    void updateStatusByTrue();

}