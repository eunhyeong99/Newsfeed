package com.team24.newsfeed.repository;


import com.team24.newsfeed.domain.Subscribe;
import com.team24.newsfeed.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    void delete(long toUserId, User fromUser);
}
