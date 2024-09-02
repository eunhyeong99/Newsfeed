package com.team24.newsfeed.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupRequestDto {

    @Email
    @NotBlank
    private String username;


    @NotBlank
    private String password;

    private boolean admin = false;

    private String adminToken = "";
}
