package com.team24.newsfeed.service;


import com.team24.newsfeed.domain.Subscribe;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.exception.DuplicateSubscribeException;
import com.team24.newsfeed.repository.SubscribeRepository;
import com.team24.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


        boolean existsRelation = subscribeRepository.existsByFriendIdAndUserId(friend.getId(), id);


        if (existsRelation) {
            throw new DuplicateSubscribeException("이미 존재하는 친구입니다.");
        } else {
            Subscribe subscribe = new Subscribe(friend, requestUser);
            subscribeRepository.save(subscribe);
        }


    }

    @Transactional
    public void deleteSubscribe(long deleteFriendId, User requestUser) {

        User deletefriend = findUser(deleteFriendId);
        long id = requestUser.getId();

        boolean existsFriend = subscribeRepository.existsByFriendIdAndUserId(deletefriend.getId(), id);

        if (existsFriend) {
            Subscribe subscribe = subscribeRepository.findByFriendIdAndUserId(deletefriend.getId(), id);
            subscribeRepository.delete(subscribe);

        } else {
            throw new RuntimeException("존재하지 않는 친구 입니다.");
        }


    }

    @Transactional
    public List<Long> findUsers(User requestUser) {
        long id = requestUser.getId();
        List<Subscribe> subscribes = subscribeRepository.findByUserId(id);
        List<Long> frinedIds = new ArrayList<>();
        for(Subscribe subscribe :subscribes ){
            frinedIds.add(subscribe.getFriend().getId());
        }

        return frinedIds;
    }

    private User findUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("선택한 userId는 존재하지 않습니다.")
        );
    }
}