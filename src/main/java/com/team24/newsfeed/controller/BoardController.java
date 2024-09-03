package com.team24.newsfeed.controller;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.dto.request.BoardUpdateDto;
import com.team24.newsfeed.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


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


    // 자신의 게시물 조회 (페이징 처리)
    @GetMapping("user={user_id}&page={page}")
    public ResponseEntity<Page<Board>> getFeeds(@RequestParam(defaultValue = "0") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Pageable pageable = PageRequest.of(page, 10);
        Page<Board> boards = boardService.getFeeds(username, pageable);
        return ResponseEntity.ok(boards);
    }

    // 게시물 수정
    @PutMapping("/{feedId}")
    public ResponseEntity<Board> updateFeed(@PathVariable Long id, @RequestBody BoardUpdateDto boardRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Board updatedBoard = boardService.updateFeed(id, username, boardRequestDto);
        return ResponseEntity.ok(updatedBoard);
    }

    // 게시물 삭제
    @DeleteMapping("/{feedId}")
    public ResponseEntity<String> deleteFeed(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boardService.deleteFeed(id, username);
        return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
    }
}
