package com.team24.newsfeed.controller;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.dto.request.BoardUpdateDto;
import com.team24.newsfeed.service.BoardService;
import org.springframework.data.domain.Page;
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


    // 게시물 수정
    @PutMapping("/{feedId}")
    public ResponseEntity<Board> updateFeed(@PathVariable Long id, @RequestBody BoardUpdateDto boardUpdateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Board updatedBoard = boardService.updateFeed(id, user.getId(), boardUpdateDto);
        return ResponseEntity.ok(updatedBoard);
    }


    // 게시물 삭제
    @DeleteMapping("/{feedId}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long feedId) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // 서비스 메서드를 통해 게시물 삭제
        boardService.deleteFeed(feedId, user.getId());
        return ResponseEntity.noContent().build();  // 삭제 성공 시 204 No Content 응답
    }

    // 뉴스피드 조회 (본인의 게시물만 조회)
    @GetMapping
    public ResponseEntity<Page<Board>> getMyFeeds(Pageable pageable) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // 본인의 게시물만 조회, 최신순 내림차순 정렬 및 페이지네이션 적용
        Page<Board> feeds = boardService.getFeedsByUser(user.getId(), pageable);
        return ResponseEntity.ok(feeds);
    }


}
