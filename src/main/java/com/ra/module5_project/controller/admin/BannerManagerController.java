package com.ra.module5_project.controller.admin;


import com.ra.module5_project.model.dto.banner.request.BannerRequestDTO;
import com.ra.module5_project.model.entity.Banner;
import com.ra.module5_project.service.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/admin/banners")
public class BannerManagerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping
    public ResponseEntity<List<Banner>> getAllBanners() {
   return ResponseEntity.ok(bannerService.getAllBanners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable Long id) {
        return ResponseEntity.ok(bannerService.getBannerById(id));
    }

    @PostMapping
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        try {
            Banner createdBanner = bannerService.save(banner);
            return ResponseEntity.ok(createdBanner);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(@RequestBody Banner banner, @PathVariable Long id) {
        try {
            Banner updatedBanner = bannerService.update(banner, id);
            return ResponseEntity.ok(updatedBanner);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        bannerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
