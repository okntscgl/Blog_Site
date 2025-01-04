package com.okntscgl.Blog.service;


import com.okntscgl.Blog.model.Post;
import com.okntscgl.Blog.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with id " + id + " not found"));

        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setCreatedAt(postDetails.getCreatedAt());
        post.setUpdatedAt(postDetails.getUpdatedAt());
        post.setStatus(postDetails.getStatus());

        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with id " + id + " not found"));

        postRepository.delete(post);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Custom Exception inside the Service Class
    public static class PostNotFoundException extends RuntimeException {
        public PostNotFoundException(String message) {
            super(message);
        }
    }
}
