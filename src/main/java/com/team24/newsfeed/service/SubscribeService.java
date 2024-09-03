package com.team24.newsfeed.service;

import com.team24.newsfeed.domain.Friend;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.repository.FriendRepository;
import com.team24.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    //친구 요청
    /*
    id : 현재 로그인한 유저
    userId : 친구요청할 유저 id
    * */

    public String requestFriend(User user, Long userId) {

        //친구 요청할 usereId
        User responseUser = findUser(userId);

//        Friend friend = new Friend(user, responseUser);
//        friendRepository.save(friend);
            user.addFriendList(userId);

    }

    private User findUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("선택한 userId는 존재하지 않습니다.")
        );
    }



}
