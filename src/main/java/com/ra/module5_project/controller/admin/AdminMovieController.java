package com.ra.module5_project.controller.admin;

import com.ra.module5_project.exception.BadRequestException;
import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.movie.MovieDTO;
import com.ra.module5_project.model.dto.movie.MovieUpdateDTO;
import com.ra.module5_project.model.entity.Movie;
import com.ra.module5_project.service.movie.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/admin/movies")
public class AdminMovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAllMovies(@RequestParam(defaultValue = "") String search, @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Movie> movies;
        if (search == null || search.isEmpty()) {
            movies = movieService.findAll(pageable);
        } else {
            movies = movieService.search(search, pageable);
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getMovieByMonth")
    public ResponseEntity<List<Movie>> getMovieByMonth(){
        return new ResponseEntity<>(movieService.getMovieByMonth(),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody MovieDTO movieDTO) throws CustomException {
        Movie movie = movieService.create(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody MovieUpdateDTO movieUpdateDTO) throws BadRequestException {
        Movie movie = movieService.update(movieUpdateDTO, id);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
