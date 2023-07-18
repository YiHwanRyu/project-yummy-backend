package com.inno.yummy.service;

import com.inno.yummy.dto.CheckUsernameRequestDto;
import com.inno.yummy.dto.LoginRequestDto;
import com.inno.yummy.dto.MessageResponseDto;
import com.inno.yummy.dto.SignupRequestDto;
import com.inno.yummy.entity.User;
import com.inno.yummy.jwt.JwtUtil;
import com.inno.yummy.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 아이디 중복 확인
    public MessageResponseDto checkUsername(CheckUsernameRequestDto checkUsernameRequestDto) {
        if(userRepository.findByUsername(checkUsernameRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }
        return new MessageResponseDto(HttpStatus.OK.toString(), true);
    }

    // 회원가입
    public MessageResponseDto createUser(SignupRequestDto signupRequestDto) {
        if(userRepository.findByUsername(signupRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }
        if(userRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        User user = new User(signupRequestDto.getUsername(), signupRequestDto.getName(), signupRequestDto.getEmail(),
                passwordEncoder.encode(signupRequestDto.getPassword()));

        userRepository.save(user);

        return new MessageResponseDto(HttpStatus.OK.toString(), true);
    }

    // 로그인
    public ResponseEntity<MessageResponseDto> loginUser(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // 아이디 확인
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        }

        //JWT 생성
        String token = jwtUtil.createToken(user.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtUtil.AUTHORIZATION_HEADER, token);

        return new ResponseEntity<>(new MessageResponseDto(HttpStatus.OK.toString(), true), headers, HttpStatus.OK);
    }


}
