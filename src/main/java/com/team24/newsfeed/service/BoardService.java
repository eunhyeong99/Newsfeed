package com.team24.newsfeed.service;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.Subscribe;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.dto.request.BoardUpdateDto;
import com.team24.newsfeed.exception.CustomException;
import com.team24.newsfeed.exception.NewsfeedException;
import com.team24.newsfeed.exception.NewsfeedExceptionConst;
import com.team24.newsfeed.repository.BoardRepository;
import com.team24.newsfeed.repository.SubscribeRepository;
import com.team24.newsfeed.repository.UserRepository;
import org.hibernate.mapping.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository, SubscribeRepository subscribeRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.subscribeRepository = subscribeRepository;
    }

    public Board createFeed(Long id, BoardCreateDto boardRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NewsfeedException(NewsfeedExceptionConst.INVALID_USER));

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
        // Find the current user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));


        // Get the list of users this user has subscribed to
        List<Board> subscriptions = subscribeRepository. findBoardsByUserFriends(user.getId());
        System.out.println(subscriptions);

        // Collect all subscribed users
        List<User> subscribedUsers = subscriptions.stream()
                .map(Board::getUser)
                .collect(Collectors.toList());


        // Add the current user to the list of subscribed users
        subscribedUsers.add(user);

        // Fetch the posts from both the current user and their subscribed users
        return boardRepository.findByUserInOrderByCreatedAtDesc(subscribedUsers, pageable);
    }

    public Board updateFeed(Long id,  BoardUpdateDto boardRequestDto) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NewsfeedException(NewsfeedExceptionConst.BOARD_NOT_FOUND));

        board.setTitle(boardRequestDto.getTitle());
        board.setContents(boardRequestDto.getContents());
        board.setModifiedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }

    public void deleteFeed(Long id, String username) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NewsfeedException(NewsfeedExceptionConst.BOARD_NOT_FOUND));
        if (!board.getUser().getUsername().equals(username)) {
            throw new NewsfeedException(NewsfeedExceptionConst.UNAUTHORIZED_ACCESS);
        }
        boardRepository.delete(board);
    }

}
