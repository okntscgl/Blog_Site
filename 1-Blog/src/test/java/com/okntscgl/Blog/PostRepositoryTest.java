package com.okntscgl.Blog;

import com.okntscgl.Blog.model.Post;
import com.okntscgl.Blog.model.PostStatus;
import com.okntscgl.Blog.model.User;
import com.okntscgl.Blog.repository.PostRepository;
import com.okntscgl.Blog.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void testSavePost() {

        User user = new User("John", "Doe", "john_doe", "john@example.com", "password123");
        userRepository.save(user);


        Post post = new Post("First Post", "This is the content of the first post.",
                LocalDateTime.now(), PostStatus.PUBLISHED, user);

        Post savedPost = postRepository.save(post);

        assertNotNull(savedPost);
        assertTrue(savedPost.getId() > 0);
        assertEquals("First Post", savedPost.getTitle());
    }

    @Test
    @Order(2)
    public void testFindByTitle() {
        Post post = new Post("Second Post", "Content of second post", LocalDateTime.now(), PostStatus.PUBLISHED,
                userRepository.findByUserName("john_doe").orElse(null));
        postRepository.save(post);

        List<Post> posts = postRepository.findByTitleContainingIgnoreCase("second");

        assertFalse(posts.isEmpty());
        assertTrue(posts.stream().anyMatch(p -> p.getTitle().equals("Second Post")));
    }

    @Test
    @Order(3)
    public void testFindByStatus() {

        User user = userRepository.findByUserName("john_doe").orElseThrow();


        Post post1 = new Post("Post 1", "Content of post 1", LocalDateTime.now(), PostStatus.PUBLISHED, user);
        Post post2 = new Post("Post 2", "Content of post 2", LocalDateTime.now(), PostStatus.DRAFT, user);
        postRepository.save(post1);
        postRepository.save(post2);


        List<Post> posts = postRepository.findByStatus(PostStatus.PUBLISHED);


        assertFalse(posts.isEmpty());
        posts.forEach(post -> assertEquals(PostStatus.PUBLISHED, post.getStatus()));
    }

    @Test
    @Order(4)
    public void testFindByUser() {


        User user = userRepository.findByUserName("john_doe").orElseThrow(() ->
                new RuntimeException("User not found"));


        Post post1 = new Post("Post 1", "Content of post 1", LocalDateTime.now(), PostStatus.PUBLISHED, user);
        Post post2 = new Post("Post 2", "Content of post 2", LocalDateTime.now(), PostStatus.DRAFT, user);
        postRepository.save(post1);
        postRepository.save(post2);


        List<Post> posts = postRepository.findByUser(user);


        assertFalse(posts.isEmpty(), "Posts should not be empty");
        posts.forEach(post -> {
            assertEquals(user, post.getUser(), "User should match");
        });
    }

    @Test
    @Order(5)
    public void testSearchByTitle() {
        Post post = new Post("Searchable Post", "This post is for search testing",
                LocalDateTime.now(), PostStatus.PUBLISHED,
                userRepository.findByUserName("john_doe").orElse(null));
        postRepository.save(post);

        List<Post> posts = postRepository.searchByTitle("searchable");

        assertFalse(posts.isEmpty());
        assertEquals("Searchable Post", posts.get(0).getTitle());
    }
}
