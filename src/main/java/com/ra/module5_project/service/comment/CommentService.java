package com.ra.module5_project.service.comment;

import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.model.dto.comment.CommentDTO;
import com.ra.module5_project.model.dto.comment.ReplyComment;
import com.ra.module5_project.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    Page<Comment> findAllComments(Pageable pageable);
    List<Comment> findAllByMovieId(Long movieId);
    Comment createByMovieId(CommentDTO commentDTO, Long movieId) throws CustomException;
    Comment updateCommentByMovieId(CommentDTO commentDTO,Long id) throws CustomException;
    void deleteCommentByMovieId(Long movieId) throws CustomException;
    Comment updateStatusByCommentId(Long commentId) throws CustomException;
    Comment replyToComment(Long commentId, ReplyComment replyComment) throws CustomException;
}
