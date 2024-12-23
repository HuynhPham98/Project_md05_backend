package com.ra.module5_project.controller.permitAll;

import com.ra.module5_project.model.entity.Movie;
import com.ra.module5_project.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/movies")
public class ControllerMovie {
    @Autowired
    private MovieService movieService;

    @GetMapping("/getMovieByMonth")
    public ResponseEntity<List<Movie>> getMovieByMonth(){
        return new ResponseEntity<>(movieService.getMovieByMonth(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }
}
