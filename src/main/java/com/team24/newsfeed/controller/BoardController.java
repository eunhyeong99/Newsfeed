package com.team24.newsfeed.controller;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.dto.request.BoardUpdateDto;
import com.team24.newsfeed.exception.CustomException;
import com.team24.newsfeed.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/feeds")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    // 게시물 작성
    @PostMapping
    public ResponseEntity<Board> createFeed(@RequestBody BoardCreateDto boardCreateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Board createdBoard = boardService.createFeed(user.getId(), boardCreateDto);
        return ResponseEntity.ok(createdBoard);
    }

    // 게시물 수정
// 게시물 수정
    @PutMapping("/{feedId}")
    public ResponseEntity<Board> updateFeed(@PathVariable Long id, @RequestBody BoardUpdateDto boardUpdateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Board updatedBoard = boardService.updateFeed(id, user.getId(), boardUpdateDto);
        return ResponseEntity.ok(updatedBoard);
    }
}
