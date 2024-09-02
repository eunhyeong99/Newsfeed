package com.team24.newsfeed.service;


import com.team24.newsfeed.config.PasswordUtils;

import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.domain.UserRoleEnum;
import com.team24.newsfeed.dto.request.SignupRequestDto;
import com.team24.newsfeed.repository.UserRepository;
import com.team24.newsfeed.security.UserDetailsImpl;
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

        // 중복된 사용자 아아디로 회원가입을 하는 경우
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        if (!PasswordUtils.isValidPassword(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호는 대소문자 영문, 숫자, 특수문자를 각각 1글자 이상 포함하고, 최소 8글자 이상이어야 합니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());


        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록

        User user = new User(username, password, role);
        userRepository.save(user);
    }

    public void deleteUser(Long id, UserDetailsImpl userDetails) {
         User newUser = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id+"가 존재하지 않습니다."));


        if (userDetails.getUsername().equals(newUser.getUsername()) && passwordEncoder.matches(userDetails.getPassword(), newUser.getPassword())) {
            userRepository.delete(newUser);
        } else {
            throw new IllegalArgumentException("비밀번호 또는 아이디가 일치하지 않습니다.");
        }

         userRepository.delete(newUser);
    }

}