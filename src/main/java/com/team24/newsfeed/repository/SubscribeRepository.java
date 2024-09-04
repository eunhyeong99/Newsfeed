package com.team24.newsfeed.repository;

import com.team24.newsfeed.domain.Subscribe;
import com.team24.newsfeed.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Subscribe findByFriendIdAndUserId(Long friend_id, long user_id);

    boolean existsByFriendIdAndUserId(Long friend_id, long user_id);


    List<Subscribe> findByUserId(long id);
    // 사용자 기준으로 구독 정보 조회
    List<Subscribe> findByUser(User user);

    List<Subscribe> findByFriend(User friend);
}