package com.ra.module5_project.repository;

import com.ra.module5_project.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMovieIdAndStatus(Long movieId,boolean status);
    List<Comment> findAllByMovieId(Long movieId);
    void deleteByMovieId(Long movieId);
}
