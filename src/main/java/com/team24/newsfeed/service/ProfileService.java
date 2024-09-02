package com.team24.newsfeed.service;

import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.dto.request.PfRequestDto;
import com.team24.newsfeed.dto.response.PfResponseDto;
import com.team24.newsfeed.exception.InvalidPasswordPatternException;
import com.team24.newsfeed.exception.NotSamePasswordException;
import com.team24.newsfeed.exception.SameUpatePasswordException;
import com.team24.newsfeed.repository.UserRepository;
import com.team24.newsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //비밀번호 패턴
    public static final String PASSWORD_CONDITION =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\p{P}\\p{S}])[a-zA-Z\\d\\p{P}\\p{S}]{8,}$";

    @Transactional
    public void updateProfile(UserDetailsImpl userDetailImpl, PfRequestDto pfRequestDto) {
        User user = userDetailImpl.getUser();

        String currentPassword = pfRequestDto.getCurrentPassword();
        String updatePassword = pfRequestDto.getUpdatePassword();

        // `찾은 유저`랑 `수정을 원하는 유저`가 입력한 비밀번호가 일치하는 지 확인
        if (passwordEncoder.matches(currentPassword, userDetailImpl.getPassword())) {
            throw new NotSamePasswordException("사용자 비밀번호가 일치하지 않습니다.");
        }

        //비밀번호 형식에 부합하는 지 확인하는 검증 로직
        if (NotAccordPasswordPattern(updatePassword)) {
            throw new InvalidPasswordPatternException("비밀번호 형식이 일치하지 않습니다.");
        }

        //변경하려는 비밀번호와 현재 비밀번호가 같은 지 확인
        if (passwordEncoder.matches(updatePassword, user.getPassword())) {
            throw new SameUpatePasswordException("이전 비밀번호랑 같습니다.");
        }

        user.setPassword(updatePassword);
    }

    private boolean NotAccordPasswordPattern(String updatePassword) {
        return !Pattern.matches(PASSWORD_CONDITION, updatePassword);
    }

    //id -> 조회 하려는 id
    //userDetailImpl -> 현 사용자의 id
    public PfResponseDto findProfile(UserDetailsImpl userDetailImpl, Long id) {

        Long loginId = userDetailImpl.getUser().getId();

        User findUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("없는 유저입니다."));

        return new PfResponseDto(findUser.getUsername(), findUser.getEmail(), findUser.getBoardList());
    }
}
