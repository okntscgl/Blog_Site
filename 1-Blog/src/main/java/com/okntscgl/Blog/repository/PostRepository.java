package com.okntscgl.Blog.repository;

import com.okntscgl.Blog.model.Post;
import com.okntscgl.Blog.model.PostStatus;
import com.okntscgl.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByStatus(PostStatus status);

    List<Post> findByUser(User user);

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Post> searchByTitle(@Param("title") String title);
}
