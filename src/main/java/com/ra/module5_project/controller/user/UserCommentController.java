package com.ra.module5_project.controller.user;

import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.comment.CommentDTO;
import com.ra.module5_project.model.entity.Comment;
import com.ra.module5_project.service.comment.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myService.com/v1/user/comments")
public class UserCommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<?> findAllByMovieId(@PathVariable("movieId") Long movieId) {
        List<Comment> comments = commentService.findAllByMovieId(movieId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/movie/{movieId}/add")
    public ResponseEntity<?> add( @PathVariable("movieId") Long movieId,@Valid @RequestBody CommentDTO commentDTO) throws CustomException {
        Comment comments = commentService.createByMovieId(commentDTO,movieId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/movie/{movieId}/update")
    public ResponseEntity<?> update(@PathVariable("movieId") Long movieId,@Valid @RequestBody CommentDTO commentDTO) throws CustomException {
        Comment comment = commentService.updateCommentByMovieId(commentDTO,movieId);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/movie/{movieId}/delete")
    public ResponseEntity<?> delete(@PathVariable("movieId") Long movieId) throws CustomException {
        commentService.deleteCommentByMovieId(movieId);
        return ResponseEntity.ok().build();
    }

}
