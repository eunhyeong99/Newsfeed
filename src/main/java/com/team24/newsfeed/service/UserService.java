package com.team24.newsfeed.service;

<<<<<<< HEAD
=======
import com.team24.newsfeed.config.PasswordUtils;
>>>>>>> eunhyeong
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.domain.UserRoleEnum;
import com.team24.newsfeed.dto.request.SignupRequestDto;
import com.team24.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
<<<<<<< HEAD
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
=======

        // 중복된 사용자 아아디로 회원가입을 하는 경우
>>>>>>> eunhyeong
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

<<<<<<< HEAD
        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }
=======
        if (!PasswordUtils.isValidPassword(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호는 대소문자 영문, 숫자, 특수문자를 각각 1글자 이상 포함하고, 최소 8글자 이상이어야 합니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());



//        // username 중복확인
//        String email = requestDto.getUsername();
//        Optional<User> checkEmail = userRepository.findByEmail(email);
//        if (checkEmail.isPresent()) {
//            throw new IllegalArgumentException("중복된 Email 입니다.");
//        }
>>>>>>> eunhyeong

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
<<<<<<< HEAD
        User user = new User(username, password, email, role);
=======
        User user = new User(username, password, role);
>>>>>>> eunhyeong
        userRepository.save(user);
    }
}