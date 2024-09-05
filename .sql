CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      boardList JSON,  -- Assuming this is a list of boards or board references
                      createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                      modifiedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE board (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       contents TEXT NOT NULL,
                       user_id BIGINT,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       CONSTRAINT fk_board_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE subscribe (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           friend_id BIGINT NOT NULL,
                           user_id BIGINT NOT NULL,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           CONSTRAINT fk_friend FOREIGN KEY (friend_id) REFERENCES user(id),
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id)
);