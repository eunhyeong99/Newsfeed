package com.team24.newsfeed.service;


import com.team24.newsfeed.domain.Subscribe;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.repository.SubscribeRepository;
import com.team24.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;


    @Transactional
    public void saveSubscribe(long toUserId, User fromUser) {
        User user = findUser(toUserId);
        long userId = user.getId();

        Subscribe subscribe = new Subscribe(userId, fromUser);

        subscribeRepository.save(subscribe);
    }

    @Transactional
    public void deleteSubscribe(long toUserId, User fromUser) {
        User user = findUser(toUserId);
        long userId = user.getId();
        subscribeRepository.delete(userId, fromUser);

    }

    private User findUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("선택한 userId는 존재하지 않습니다.")
        );
    }

}
