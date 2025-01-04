package com.okntscgl.Blog.controller;

import com.okntscgl.Blog.model.Comment;
import com.okntscgl.Blog.service.CommentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        return commentService.updateComment(id, updatedComment);
    }
}
