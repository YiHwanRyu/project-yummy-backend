package com.inno.yummy.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    // 숫자 or 영문 총 3~10자
    @Pattern(regexp = "[a-z0-9]{3,10}")
    private String username;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    //영대문자, 소문자, 숫자, 특수문자 무조건 각각 1개이상 포함시켜서 8자 ~ 15자 구성
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,15}$")
    private String password;
}
