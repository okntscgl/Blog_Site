package com.okntscgl.Blog;

import com.okntscgl.Blog.model.User;
import com.okntscgl.Blog.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void testSave() {
        User user = new User(
                "John",
                "Doe",
                "john_doe",
                "john@example.com",
                "password123");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertTrue(savedUser.getId() > 0);
    }

    @Test
    @Order(2)
    public void testFindByUserName() {
        User user = userRepository.findByUserName("john_doe").orElse(null);

        assertNotNull(user);
        assertEquals("john_doe", user.getUserName());
    }

    @Test
    @Order(3)
    public void testFindByEmail() {
        User user = userRepository.findByEmail("john@example.com").orElse(null);

        assertNotNull(user);
        assertEquals("john_doe", user.getUserName());
    }

    @Test
    @Order(4)
    public void testFindByUserNameOrEmail() {
        Optional<User> userByUserName = userRepository.findByUserNameOrEmail("john_doe", null);
        Optional<User> userByEmail = userRepository.findByUserNameOrEmail(null, "john@example.com");

        assertTrue(userByUserName.isPresent());
        assertTrue(userByEmail.isPresent());
    }

    @Test
    @Order(5)
    public void testExistsByUserName() {
        User user = new User(
                "Unique",
                "User",
                "unique_user",
                "unique@example.com",
                "password123");

        userRepository.save(user);

        boolean exists = userRepository.existsByUserName("unique_user");

        assertTrue(exists);
    }

    @Test
    @Order(6)
    public void testExistsByEmail() {
        User user = new User(
                "Another",
                "User",
                "another_user",
                "another@example.com",
                "password123");

        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("another@example.com");

        assertTrue(exists);
    }
}
