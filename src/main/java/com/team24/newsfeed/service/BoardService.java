package com.team24.newsfeed.service;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.dto.request.BoardUpdateDto;
import com.team24.newsfeed.exception.CustomException;
import com.team24.newsfeed.repository.BoardRepository;
import com.team24.newsfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

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
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("사용자를 찾을 수 없습니다."));

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

    public Board updateFeed(Long id, Long user_id, BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException("게시물을 찾을 수 없습니다."));

        if (!board.getUser().getId().equals(user_id)) {
            throw new CustomException("게시물 수정 권한이 없습니다.");
        }

        board.setTitle(boardUpdateDto.getTitle());
        board.setContents(boardUpdateDto.getContents());
        board.setModifiedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }
}
