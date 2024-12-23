package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
