package com.team24.newsfeed.service;

import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.ProfileUpdateDto;
import com.team24.newsfeed.dto.response.ProfileResponseDto;
import com.team24.newsfeed.exception.profile.InvalidPasswordPatternException;
import com.team24.newsfeed.exception.profile.NotSamePasswordException;
import com.team24.newsfeed.exception.profile.SameUpdatePasswordException;
import com.team24.newsfeed.repository.UserRepository;
import com.team24.newsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //비밀번호 패턴 정규식 -> 알파벳 1개 이상, 숫자 1개 이상, 모든 기호중 1개 이상, 8자 이상
    public static final String PASSWORD_CONDITION =
            "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\p{P}\\p{S}])[a-zA-Z\\d\\p{P}\\p{S}]{8,}$";

    @Transactional
    public void updateProfile(UserDetailsImpl userDetailImpl, ProfileUpdateDto updateDto) {
        User user = userDetailImpl.getUser();

        User findUser = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("없는 유저입니다."));

        String updatePassword = updateDto.getUpdatePassword();
        String currentPassword = updateDto.getCurrentPassword();

        // `찾은 유저`랑 `수정을 원하는 유저`가 입력한 비밀번호가 일치하는 지 확인
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            log.info("유저 일치 예외");
            throw new NotSamePasswordException();
        }

        //비밀번호 형식에 부합하는 지 확인하는 검증 로직
        if (!Pattern.matches(PASSWORD_CONDITION, updatePassword)) {
            log.info("비밀번호 형식 예외");
            throw new SameUpdatePasswordException();

        }

        //변경하려는 비밀번호와 현재 비밀번호가 같은 지 확인
        if (passwordEncoder.matches(updatePassword, user.getPassword())) {
            log.info("현재 비밀번호와 같은 예외");
            throw new InvalidPasswordPatternException();
        }

        log.info("updatePassword={}", updatePassword);
        String encodePassword = passwordEncoder.encode(updatePassword);

        findUser.setPassword(encodePassword);
    }

    //id -> 조회 하려는 id
    //userDetailImpl -> 현 사용자의 id
    //현 사용자 == 조회유저 -> 민감한 정보 가려야 하는 것인가...
    public ProfileResponseDto findProfile(UserDetailsImpl userDetailImpl, Long userId) {

        Long loginId = userDetailImpl.getUser().getId();
        User findUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("없는 유저입니다."));

        //if문으로 확인 -> 본인 || 다른 사용자 -> 민감한 정보 격리수준 분리
        if (loginId.equals(userId)) {
            return new ProfileResponseDto(findUser.getUsername(), findUser.getBoardList());
        } else {
            return new ProfileResponseDto(findUser.getUsername(), null);
        }
    }
}