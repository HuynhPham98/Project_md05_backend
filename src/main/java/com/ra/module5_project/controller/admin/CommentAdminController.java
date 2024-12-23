package com.ra.module5_project.controller.admin;

import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.comment.ReplyComment;
import com.ra.module5_project.model.entity.Comment;
import com.ra.module5_project.service.comment.CommentService;
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
@RequestMapping("/api.myService.com/v1/admin/comments")
public class CommentAdminController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<?> findAllComments(@PageableDefault(page = 0, size = 5,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Comment> comments = commentService.findAllComments(pageable);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/reply/{commentId}")
    public ResponseEntity<?> reply(@PathVariable Long commentId, @Valid @RequestBody ReplyComment replyComment) throws CustomException {
        Comment reply = commentService.replyToComment(commentId,replyComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(reply);
    }

    @PutMapping("/updateStatus/{commentId}")
    public ResponseEntity<?> updateStatus( @PathVariable Long commentId) throws CustomException {
        Comment comment = commentService.updateStatusByCommentId(commentId);
        return ResponseEntity.ok(comment);
    }
}
