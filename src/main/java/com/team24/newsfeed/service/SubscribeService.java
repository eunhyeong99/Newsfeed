package com.team24.newsfeed.service;


import com.team24.newsfeed.domain.Subscribe;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.exception.DuplicateSubscribeException;
import com.team24.newsfeed.repository.SubscribeRepository;
import com.team24.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;


    @Transactional
    public void saveSubscribe(long friendId, User requestUser) {
        //내가 follow
        User friend = findUser(friendId);
        long id = requestUser.getId();

        //반대인 경우
        long reveresId = requestUser.getId();
        long reveresFriendId = friendId;

        boolean alreadyExists = subscribeRepository.existsByFriendIdAndUserId(friend.getId(), id);
        boolean reversFriend = subscribeRepository.existsByFriendIdAndUserId(reveresId, reveresFriendId);

        if (alreadyExists || reversFriend) {
            throw new DuplicateSubscribeException("이미 존재하는 친구입니다.");
        }else{
            Subscribe subscribe = new Subscribe(friend, requestUser);
            subscribeRepository.save(subscribe);
        }


    }

    @Transactional
    public void deleteSubscribe(long friendId, User requestUser) {
        User friend = findUser(friendId);
        long id = requestUser.getId();
        Subscribe subscribe = subscribeRepository.findByFriendIdAndUserId(friend.getId(), id);

        subscribeRepository.delete(subscribe);
    }

    private User findUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("선택한 userId는 존재하지 않습니다.")
        );
    }

    public List findUsers() {
        return userRepository.findAll();
    }
}
