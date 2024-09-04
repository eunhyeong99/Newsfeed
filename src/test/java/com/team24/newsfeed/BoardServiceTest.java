package com.team24.newsfeed;

import com.team24.newsfeed.domain.Board;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.BoardCreateDto;
import com.team24.newsfeed.exception.NewsfeedException;
import com.team24.newsfeed.repository.BoardRepository;
import com.team24.newsfeed.repository.UserRepository;
import com.team24.newsfeed.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateFeed_Success() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setUser_id(userId);
        user.setUsername("testUser");

        BoardCreateDto boardCreateDto = new BoardCreateDto("Test Title", "Test Content");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Board board = Board.builder()
                .user(user)
                .title(boardCreateDto.getTitle())
                .contents(boardCreateDto.getContents())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        when(boardRepository.save(any(Board.class))).thenReturn(board);

        // Act
        Board createdBoard = boardService.createFeed(userId, boardCreateDto);

        // Assert
        assertNotNull(createdBoard);
        assertEquals(boardCreateDto.getTitle(), createdBoard.getTitle());
        assertEquals(boardCreateDto.getContents(), createdBoard.getContents());
    }

    private Board any(Class<Board> boardClass) {
        return null;
    }

    @Test(expected = NewsfeedException.class)
    public void testCreateFeed_UserNotFound() {
        // Arrange
        Long userId = 1L;
        BoardCreateDto boardCreateDto = new BoardCreateDto("Test Title", "Test Content");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        boardService.createFeed(userId, boardCreateDto);
    }
}