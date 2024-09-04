package com.team24.newsfeed.controller;

import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.exception.DuplicateSubscribeException;
import com.team24.newsfeed.security.UserDetailsImpl;
import com.team24.newsfeed.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubscribeController {

    private final SubscribeService subscribeService;

    //팔로우
    @PostMapping("/subscribe/{friendId}")
    public ResponseEntity<String>subscribe(@PathVariable Long friendId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        User requestUser = userDetails.getUser();

        try {
            subscribeService.saveSubscribe(friendId, requestUser);
            return new ResponseEntity<>("구독완료",HttpStatus.OK);
        }catch(DuplicateSubscribeException e){
        return new ResponseEntity<>(e.getDetailMessage(), HttpStatus.CONFLICT);
        }
    }

    //언팔로우
    @DeleteMapping("/subscribe/{friendId}")
    public ResponseEntity<String> Subscribe(@PathVariable long friendId, @AuthenticationPrincipal UserDetailsImpl userDetails){

        User requestUser = userDetails.getUser();

        subscribeService.deleteSubscribe( friendId, requestUser);

        return ResponseEntity.ok().body("구독해제성공");
    }

    //친구 검색
    @GetMapping("/findUsers")
    public ResponseEntity<List> findUsers(){
        List users = subscribeService.findUsers();
        return ResponseEntity.ok().body(users);
    }

}
