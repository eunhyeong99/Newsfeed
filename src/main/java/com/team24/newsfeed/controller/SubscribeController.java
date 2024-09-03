package com.team24.newsfeed.controller;

import com.team24.newsfeed.domain.Friend;
import com.team24.newsfeed.domain.User;
import com.team24.newsfeed.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {
    private final FriendService friendService;

    @PutMapping("/{userId}")
    public String friendRequest(@PathVariable Long userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= (User) authentication.getPrincipal();

        friendService.requestFriend(user, userId);


      return "친구요청 완료";
    }


}
