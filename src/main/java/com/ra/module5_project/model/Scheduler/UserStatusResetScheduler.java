package com.ra.module5_project.model.Scheduler;

import com.ra.module5_project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserStatusResetScheduler {
    @Autowired
    private UserRepository userRepository; // Repository của bạn

    @Scheduled(cron = "0 0 0 * * ?") // Mỗi ngày lúc 00:00
    @Transactional
    public void resetUserStatus() {
        userRepository.updateStatusByTrue(); // Gọi phương thức update
    }
}
