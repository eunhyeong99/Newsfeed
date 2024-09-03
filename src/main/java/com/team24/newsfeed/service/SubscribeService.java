package com.team24.newsfeed.service;


import com.team24.newsfeed.domain.Subscribe;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;


    @Transactional
    public void saveSubscribe(long toUserId, User fromUser) {

        Subscribe subscribe = new Subscribe(toUserId, fromUser);

        subscribeRepository.save(subscribe);

    }



}
