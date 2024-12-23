package com.ra.module5_project.service.movie;

import com.ra.module5_project.exception.BadRequestException;
import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.movie.MovieDTO;
import com.ra.module5_project.model.dto.movie.MovieUpdateDTO;
import com.ra.module5_project.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    Page<Movie> findAll(Pageable pageable);
    Movie create(MovieDTO movieDTO) throws CustomException;
    Movie findById(Long id);
    Movie update(MovieUpdateDTO movieUpdateDTO, Long id) throws BadRequestException;
    void delete(Long id);
    Page<Movie> search(String keyword, Pageable pageable);

    // Show all user
    List<Movie> findAllMoviesLast7Days();
    List<Movie> findAllMoviesInFuture();

    List<Movie> findAllMoviesNews();
    List<Movie> getMovieByMonth();
}
