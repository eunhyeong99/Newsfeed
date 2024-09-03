package com.team24.newsfeed.service;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.dto.request.BoardUpdateDto;
import com.team24.newsfeed.exception.NewsfeedException;
import com.team24.newsfeed.exception.NewsfeedExceptionConst;
import com.team24.newsfeed.repository.BoardRepository;
import com.team24.newsfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Board createFeed(Long id, BoardCreateDto boardRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NewsfeedException("사용자를 찾을 수 없습니다."));

        LocalDateTime now = LocalDateTime.now();

        Board board = Board.builder()
                .user(user)
                .title(boardRequestDto.getTitle())
                .contents(boardRequestDto.getContents())
                .createdAt(now)
                .modifiedAt(now)
                .build();

        return boardRepository.save(board);
    }

    public Page<Board> getFeeds(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NewsfeedException(NewsfeedExceptionConst.INVALID_USER));

        return boardRepository.findByUserOrderByCreatedAtDesc(user, pageable);
    }

    public Board updateFeed(Long boardId, String username, BoardUpdateDto boardRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NewsfeedException(NewsfeedExceptionConst.BOARD_NOT_FOUND));

        if (!board.getUser().getUsername().equals(username)) {
            throw new NewsfeedException(NewsfeedExceptionConst.UNAUTHORIZED_ACCESS);
        }

        board.setTitle(boardRequestDto.getTitle());
        board.setContents(boardRequestDto.getContents());
        board.setModifiedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }

    public void deleteFeed(Long id, String username) {
    }

}
