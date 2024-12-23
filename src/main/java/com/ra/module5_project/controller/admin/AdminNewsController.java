package com.ra.module5_project.controller.admin;

import com.ra.module5_project.exception.BadRequestException;
import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.news.NewsDTO;
import com.ra.module5_project.model.dto.news.NewsUpdateDTO;
import com.ra.module5_project.model.entity.News;
import com.ra.module5_project.service.news.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myService.com/v1/admin/news")
public class AdminNewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "") String search, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<News> news;
        if(search == null || search.isEmpty()) {
            news = newsService.findAll(pageable);
        } else {
            news = newsService.search(search, pageable);
        }
        return ResponseEntity.ok(news);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody NewsDTO newsDTO) throws CustomException {
        News news = newsService.create(newsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        News news = newsService.findById(id);
        return ResponseEntity.ok(news);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@Valid @RequestBody NewsUpdateDTO newsUpdateDTO) throws CustomException, BadRequestException {
        News news = newsService.update(newsUpdateDTO, id);
        return ResponseEntity.ok(news);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
