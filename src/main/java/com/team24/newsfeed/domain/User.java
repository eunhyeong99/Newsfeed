package com.team24.newsfeed.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
=======

>>>>>>> eunhyeong
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

<<<<<<< HEAD
    @Column(nullable = false, unique = true)
    private String email;
=======
>>>>>>> eunhyeong

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();

<<<<<<< HEAD
=======
    public User(String username, String password,  UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
>>>>>>> eunhyeong
}
