package com.team24.newsfeed.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD

@Getter
public class SignupRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
=======
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
>>>>>>> eunhyeong

    private boolean admin = false;

    private String adminToken = "";
}
