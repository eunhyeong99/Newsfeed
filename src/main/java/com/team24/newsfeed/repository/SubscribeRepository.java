package com.team24.newsfeed.repository;

import com.team24.newsfeed.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
