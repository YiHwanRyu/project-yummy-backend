package com.inno.yummy.controller;

import com.inno.yummy.dto.CheckUsernameRequestDto;
import com.inno.yummy.dto.LoginRequestDto;
import com.inno.yummy.dto.MessageResponseDto;
import com.inno.yummy.dto.SignupRequestDto;
import com.inno.yummy.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/check")
    public MessageResponseDto checkUsername(@RequestBody CheckUsernameRequestDto checkUsernameRequestDto) {
        return userService.checkUsername(checkUsernameRequestDto);
    }

    @PostMapping("/sign-up")
    public MessageResponseDto createUser(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userService.createUser(signupRequestDto);
    }

    @PostMapping("/login")
    public MessageResponseDto loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.loginUser(loginRequestDto, response);
    }

}
