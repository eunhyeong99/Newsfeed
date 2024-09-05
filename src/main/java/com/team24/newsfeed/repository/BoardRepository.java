package com.team24.newsfeed.repository;

import com.team24.newsfeed.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.team24.newsfeed.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    // 사용자별 게시물 조회, 생성일자 내림차순 정렬
    Page<Board> findByUserInOrderByCreatedAtDesc(List<User> user, Pageable pageable);
    // 사용자 ID에 해당하는 게시물을 최신순으로 페이지네이션하여 조회
    Page<Board> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}