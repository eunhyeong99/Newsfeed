package com.team24.newsfeed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @GetMapping("/profile/{id}")
    public void getProfile() {

    }

    @PostMapping("/profile/modify")
    public void updatePassword() {

    }
}
