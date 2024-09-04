package com.team24.newsfeed.repository;


import com.team24.newsfeed.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    Subscribe findByFriendIdAndUserId(Long id, long id1);

    boolean existsByFriendIdAndUserId(Long id, long id1);
}
