package com.ra.module5_project.service.news;

import com.ra.module5_project.exception.BadRequestException;
import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.news.NewsDTO;
import com.ra.module5_project.model.dto.news.NewsUpdateDTO;
import com.ra.module5_project.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsService {
    Page<News> findAll(Pageable pageable);
    List<News> findAll();
    News create(NewsDTO newsDTO) throws CustomException;
    News findById(Long id);
    News update(NewsUpdateDTO newsUpdateDTO, Long id) throws CustomException, BadRequestException;
    void delete(Long id);
    Page<News> search(String keyword, Pageable pageable);
}
