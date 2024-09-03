package com.team24.newsfeed.controller;

import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscribe")
public class SubscribeController {

    @Autowired
    private final SubscribeService subscribeService;

    @PostMapping("/{toUserId}")
    public ResponseEntity<String> subscribe(@PathVariable int toUserId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User fromUser = (User) authentication.getPrincipal();

        subscribeService.saveSubscribe(toUserId, fromUser);
        return ResponseEntity.ok().body("구독성공");


    }



}
