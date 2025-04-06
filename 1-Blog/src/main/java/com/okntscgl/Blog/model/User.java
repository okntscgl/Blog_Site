package com.okntscgl.Blog.model;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "User")
@Table(
        name = "blog_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique",
                        columnNames = "email"),
                @UniqueConstraint(
                        name = "user_name_unique",
                        columnNames = "user_name")
        }
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false)
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            length = 50)
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 50)
    private String lastName;

    @Column(
            name = "user_name",
            nullable = false,
            length = 50)
    private String userName;

    @Column(
            name = "email",
            nullable = false)
    private String email;

    @Column(
            name = "password",
            nullable = false)
    private String password;

    @Column(
            name = "is_active",
            nullable = false)
    private boolean isActive = true;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Post> posts;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Comment> comments;

    public User(String firstName, String lastName,
                String userName, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
