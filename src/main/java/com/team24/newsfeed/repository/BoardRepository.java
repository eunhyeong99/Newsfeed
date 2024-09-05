package com.team24.newsfeed.repository;

import com.team24.newsfeed.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    // 사용자 ID에 해당하는 게시물을 최신순으로 페이지네이션하여 조회
    Page<Board> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
