package com.team24.newsfeed.service;

import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.ProfileUpdateDto;
import com.team24.newsfeed.dto.response.ProfileResponseDto;
import com.team24.newsfeed.exception.profileexception.InvalidPasswordPatternException;
import com.team24.newsfeed.exception.profileexception.NotSamePasswordException;
import com.team24.newsfeed.exception.profileexception.SameUpdatePasswordException;
import com.team24.newsfeed.repository.UserRepository;
import com.team24.newsfeed.security.UserDetailsImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //비밀번호 패턴
    public static final String PASSWORD_CONDITION =
            "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\p{P}\\p{S}])[a-zA-Z\\d\\p{P}\\p{S}]{8,}$";

    @Transactional
    public void updateProfile(UserDetailsImpl userDetailImpl, ProfileUpdateDto updateDto) {
        User user = userDetailImpl.getUser();

        String currentPassword = updateDto.getCurrentPassword();
        String updatePassword = updateDto.getUpdatePassword();

        // `찾은 유저`랑 `수정을 원하는 유저`가 입력한 비밀번호가 일치하는 지 확인
        if (!passwordEncoder.matches(currentPassword, userDetailImpl.getPassword())) {
            throw new NotSamePasswordException();
        }

        //비밀번호 형식에 부합하는 지 확인하는 검증 로직
        if (!Pattern.matches(PASSWORD_CONDITION, updatePassword)) {
            throw new InvalidPasswordPatternException();
        }

        //변경하려는 비밀번호와 현재 비밀번호가 같은 지 확인
        if (passwordEncoder.matches(updatePassword, user.getPassword())) {
            throw new SameUpdatePasswordException();
        }
        String encodePassword = passwordEncoder.encode(updatePassword);
        user.setPassword(encodePassword);
    }

    //id -> 조회 하려는 id
    //userDetailImpl -> 현 사용자의 id
    //현 사용자 == 조회유저 -> 민감한 정보 가려야 하는 것인가...
    public ProfileResponseDto findProfile(UserDetailsImpl userDetailImpl, Long userId) {

        //if문으로 확인 -> 본인 || 다른 사용자 -> 민감한 정보 격리수준 분리
        Long loginId = userDetailImpl.getUser().getId();

        User findUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("없는 유저입니다."));

        return new ProfileResponseDto(findUser.getUsername(), findUser.getEmail(), findUser.getBoardList());
    }
}
