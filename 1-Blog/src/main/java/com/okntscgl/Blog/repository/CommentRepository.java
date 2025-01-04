package com.okntscgl.Blog.repository;

import com.okntscgl.Blog.model.Comment;
import com.okntscgl.Blog.model.Post;
import com.okntscgl.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByApproved(boolean approved);

    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);

    List<Comment> findByParentComment(Comment parentComment);
}
