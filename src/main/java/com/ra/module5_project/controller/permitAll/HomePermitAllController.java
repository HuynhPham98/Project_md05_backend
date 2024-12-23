package com.ra.module5_project.controller.permitAll;
import com.ra.module5_project.model.entity.Comment;
import com.ra.module5_project.model.entity.Movie;
import com.ra.module5_project.model.entity.News;
import com.ra.module5_project.service.comment.CommentService;
import com.ra.module5_project.service.movie.MovieService;
import com.ra.module5_project.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/permit_all")
public class HomePermitAllController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/is_showing")
    public ResponseEntity<?> findAllMovieIsShowing() {
        List<Movie> movies = movieService.findAllMoviesLast7Days();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/coming_soon")
    public ResponseEntity<?> findAllMovieComingSoon() {
        List<Movie> movies = movieService.findAllMoviesInFuture();
        return ResponseEntity.ok(movies);
    }

    //Phim moi nhat
    @GetMapping("/movies_new")
    public ResponseEntity<?> findAllMovieNews() {
        List<Movie> movies = movieService.findAllMoviesNews();
        return ResponseEntity.ok(movies);
    }

    //Chi tiet phim
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<?> findMovieById(@PathVariable Long movieId) {
        Movie movie = movieService.findById(movieId);
        return ResponseEntity.ok(movie);
    }

    //Phan tin tuc
    @GetMapping("/news")
    public ResponseEntity<?> findAllNews() {
        List<News> newsList = newsService.findAll();
        return ResponseEntity.ok(newsList);
    }

    //Chi tiet tin tuc
    @GetMapping("/news/{newsId}")
    public ResponseEntity<?> findNewsById(@PathVariable Long newsId) {
        News news = newsService.findById(newsId);
        return ResponseEntity.ok(news);
    }

    @GetMapping("/comments/movie/{movieId}")
    public ResponseEntity<?> findAllCommentsByMovieId(@PathVariable Long movieId) {
        List<Comment> comments = commentService.findAllByMovieId(movieId);
        return ResponseEntity.ok(comments);
    }

}
