package com.ra.module5_project.service.banner;
import com.ra.module5_project.model.entity.Banner;
import org.springframework.dao.DataAccessException;
import java.util.List;


public interface BannerService {

    List<Banner> getAllBanners();
    Banner getBannerById(Long id);
    Banner save(Banner banner) throws DataAccessException;
    Banner update(Banner banner, Long id) throws DataAccessException;
    void delete(Long id);






}
