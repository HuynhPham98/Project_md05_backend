package com.ra.module5_project.service.banner;

import com.ra.module5_project.model.dto.banner.request.BannerRequestDTO;
import com.ra.module5_project.model.dto.banner.response.BannerResponseDTO;
import com.ra.module5_project.model.entity.Banner;
import com.ra.module5_project.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getBannerById(Long id) {
        return bannerRepository.findById(id).orElse(null);
    }

    @Override
    public Banner save(Banner banner) throws DataAccessException {
        return bannerRepository.save(banner);
    }

    @Override
    public Banner update(Banner banner, Long id) throws DataAccessException {
        Banner existingBanner = getBannerById(id);
        // Cập nhật các thuộc tính
        existingBanner.setImageUrl(banner.getImageUrl());
        existingBanner.setTitle(banner.getTitle());
        return bannerRepository.save(existingBanner);
    }

    @Override
    public void delete(Long id) {
        if (!bannerRepository.existsById(id)) {
            throw new RuntimeException("Banner not found");
        }
        bannerRepository.deleteById(id);
    }
}