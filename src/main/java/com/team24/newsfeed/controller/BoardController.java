package com.team24.newsfeed.controller;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.dto.request.BoardUpdateDto;
import com.team24.newsfeed.security.UserDetailsImpl;
import com.team24.newsfeed.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        // UserDetailsImpl로 캐스팅하여 사용자 정보를 가져옴
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();  // User 객체를 가져옴 !

        // 게시물 생성
        Board createdBoard = boardService.createFeed(user.getId(), boardCreateDto);

        return ResponseEntity.ok(createdBoard);
    }


    // 자신의 게시물 조회 (페이징 처리)
    @GetMapping()
    public ResponseEntity<Page<Board>> getFeeds(@RequestParam(defaultValue = "0" )
                                                @AuthenticationPrincipal UserDetailsImpl userDetails, int page) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
        String username  = userDetails.getUsername();
        Pageable pageable = PageRequest.of(page, 10);
        Page<Board> boards = boardService.getFeeds(username, pageable);
        return ResponseEntity.ok(boards);
    }

    // 게시물 수정
    @PutMapping("/{board_id}")
    public ResponseEntity<Board> updateFeed(@PathVariable Long board_id,
                                            @RequestBody BoardUpdateDto boardRequestDto
    ) {

        Board updatedBoard = boardService.updateFeed(board_id, boardRequestDto);
        return ResponseEntity.ok(updatedBoard);
    }

    // 게시물 삭제
    @DeleteMapping("/{feedId}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long feedId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        // 서비스 메서드를 통해 게시물 삭제
        boardService.deleteFeed(feedId, user.getUsername());
        return ResponseEntity.noContent().build();  // 삭제 성공 시 204 No Content 응답
    }

}
