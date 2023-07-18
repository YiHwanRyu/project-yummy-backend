package com.inno.yummy.controller;

import com.inno.yummy.dto.*;
import com.inno.yummy.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/check")
    public MessageResponseDto checkUsername(@RequestBody CheckUsernameRequestDto checkUsernameRequestDto) {
        return userService.checkUsername(checkUsernameRequestDto);
    }

    @PostMapping("/auth/sign-up")
    public MessageResponseDto createUser(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userService.createUser(signupRequestDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<MessageResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.loginUser(loginRequestDto, response);
    }

    @GetMapping("/mypage")
    public MypageResponseDto getMypage(HttpServletRequest request) {
        return userService.getMypage(request.getAttribute("username").toString());
    }


}
