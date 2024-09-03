package com.team24.newsfeed.repository;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    // 사용자별 게시물 조회, 생성일자 내림차순 정렬
    Page<Board> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
