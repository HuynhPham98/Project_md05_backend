package com.ra.module5_project.service.comment;

import com.ra.module5_project.exception.CustomException;
import com.ra.module5_project.exception.NoResourceFoundException;
import com.ra.module5_project.model.dto.comment.CommentDTO;
import com.ra.module5_project.model.dto.comment.ReplyComment;
import com.ra.module5_project.model.entity.Comment;
import com.ra.module5_project.model.entity.Movie;
import com.ra.module5_project.repository.CommentRepository;
import com.ra.module5_project.repository.MovieRepository;
import com.ra.module5_project.repository.UserRepository;
import com.ra.module5_project.security.principle.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Comment> findAllComments(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        return comments;
    }

    //Hiển thị các comment  trong phim(có status = true)
    @Override
    public List<Comment> findAllByMovieId(Long movieId) throws NoResourceFoundException {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie == null) {
            throw new NoResourceFoundException("Phim không tồn tại");
        }
        List<Comment> comments = commentRepository.findAllByMovieIdAndStatus(movieId,true);
        return comments;
    }

    @Override
    public Comment createByMovieId(CommentDTO commentDTO, Long movieId) throws CustomException {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie == null) {
            throw new NoResourceFoundException("Phim không tồn tại");
        }
        List<Comment> comments = commentRepository.findAllByMovieId(movieId);
        boolean check =comments.stream()
                .anyMatch(comment -> comment.getUser().getUsername()
                        .equals(userPrinciple.getUsername()));
        if(check) {
            throw new CustomException("Bạn đã bình luận sản phẩm này rồi");
        }
        Comment newComment = new Comment();
        newComment.setUser(userPrinciple.getUser());
        newComment.setMovie(movie);
        newComment.setComment(commentDTO.getComment());
        newComment.setStatus(true);
        commentRepository.save(newComment);
        return newComment;
    }

    @Override
    public Comment updateCommentByMovieId(CommentDTO commentDTO, Long movieId) throws CustomException {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie == null) {
            throw new NoResourceFoundException("Phim không tồn tại");
        }
        List<Comment> comments = commentRepository.findAllByMovieId(movieId);
        Comment updateComment = comments.stream()
                .filter(comment -> comment.getUser().getUsername().equals(userPrinciple.getUsername()))
                .findFirst().orElse(null);
        if(updateComment == null) {
            throw new CustomException("Bạn chưa bình luận sản phẩm này");
        }
        updateComment.setComment(commentDTO.getComment());
        commentRepository.save(updateComment);
        return updateComment;

    }

    @Override
    public void deleteCommentByMovieId(Long movieId) throws CustomException {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie == null) {
            throw new NoResourceFoundException("Phim không tồn tại");
        }
        List<Comment> comments = commentRepository.findAllByMovieId(movieId);
        boolean check =comments.stream()
                .anyMatch(comment -> comment.getUser().getUsername()
                        .equals(userPrinciple.getUsername()));
        if(!check) {
            throw new CustomException("Bạn chưa bình luận sản phẩm này");
        }
        commentRepository.deleteByMovieId(movieId);
    }

    @Override
    public Comment updateStatusByCommentId(Long commentId) throws CustomException {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) {
            throw new CustomException("Comment không tồn tại");
        }
        comment.setStatus(!comment.isStatus());
        commentRepository.save(comment);
        return comment;
    }


    @Override
    public Comment replyToComment(Long commentId, ReplyComment replyComment) throws CustomException {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Comment parentComment = commentRepository.findById(commentId).orElse(null);
        if(parentComment == null) {
            throw new CustomException("Bình luận không tồn tại");
        }

        Comment newComment = Comment.builder()
                .user(userPrinciple.getUser())
                .comment(replyComment.getReplyComment())
                .status(true)
                .parentComment(parentComment)
                .movie(parentComment.getMovie())
                .build();
        commentRepository.save(newComment);
        return newComment;
    }
}
