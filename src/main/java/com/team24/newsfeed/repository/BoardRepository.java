package com.team24.newsfeed.repository;

import com.team24.newsfeed.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
