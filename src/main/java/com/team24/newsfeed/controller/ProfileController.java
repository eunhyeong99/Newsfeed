package com.team24.newsfeed.controller;

import com.team24.newsfeed.dto.request.ProfileUpdateDto;
import com.team24.newsfeed.dto.response.ProfileResponseDto;
import com.team24.newsfeed.security.UserDetailsImpl;
import com.team24.newsfeed.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@AuthenticationPrincipal UserDetailsImpl userDetailImpl
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileResponseDto> getProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("userId") Long userId
    ) {
        ProfileResponseDto findProfile = profileService.findProfile(userDetails, userId);
        return ResponseEntity.ok(findProfile);
    }

    @PatchMapping("/profile/password")
    public ResponseEntity<Void> updatePassword(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Validated @RequestBody ProfileUpdateDto updateDto
    ) {
        profileService.updateProfile(userDetails, updateDto);
        return ResponseEntity.ok().build();
    }
}
