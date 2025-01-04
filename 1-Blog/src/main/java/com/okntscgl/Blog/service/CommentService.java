package com.okntscgl.Blog.service;

import com.okntscgl.Blog.model.Comment;
import com.okntscgl.Blog.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setContent(commentDetails.getContent());
        comment.setCreatedAt(commentDetails.getCreatedAt());
        comment.setApproved(commentDetails.isApproved());
        comment.setParentComment(commentDetails.getParentComment());

        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepository.delete(comment);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
