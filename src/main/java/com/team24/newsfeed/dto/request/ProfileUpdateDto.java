package com.team24.newsfeed.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProfileUpdateDto {

    @NotBlank
    String currentPassword;
    @NotBlank
    String updatePassword;
}
