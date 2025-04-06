package com.okntscgl.Blog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Comment")
@Table(name = "comment")
public class Comment {

    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "content",
            nullable = false,
            length = 500
    )
    private String content;

    @Column(
            name = "created_time",
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "approved",
            nullable = false
    )
    private boolean approved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "post_comment_fk"),
            nullable = false
    )
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "parent_comment_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "parent_comment_fk"),
            nullable = true
    )
    private Comment parentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "user_comment_fk")
    )
    private User user;

    public Comment(String content, LocalDateTime createdAt, boolean approved,
                   Post post, Comment parentComment, User user) {

        this.content = content;
        this.createdAt = createdAt;
        this.approved = approved;
        this.post = post;
        this.parentComment = parentComment;
        this.user = user;
    }

    public Comment() {
    }

    public Comment(String childComment, LocalDateTime now, User user, Post post, boolean b) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", approved=" + approved +
                ", post=" + post +
                ", parentComment=" + parentComment +
                ", user=" + user +
                '}';
    }
}