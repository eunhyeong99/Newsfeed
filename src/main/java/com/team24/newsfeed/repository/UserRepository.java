package com.team24.newsfeed.repository;

import com.team24.newsfeed.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
<<<<<<< HEAD
    Optional<User> findByEmail(String email);
=======
>>>>>>> eunhyeong
}
