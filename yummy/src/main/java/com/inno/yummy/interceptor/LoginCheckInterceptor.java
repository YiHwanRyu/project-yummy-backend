package com.inno.yummy.interceptor;

import com.inno.yummy.jwt.JwtUtil;
import com.inno.yummy.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Home에서의 조회는 통과시킴(조회)
        // request에서 url가져와서 비교가능
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(request.getMethod().equals("GET") && handlerMethod.getMethod().getName().equals("getHomePosts")) {
            return true;
        }
        if(request.getMethod().equals("GET") && handlerMethod.getMethod().getName().equals("getHomePostsWant")) {
            return true;
        }
        if(request.getMethod().equals("GET") && handlerMethod.getMethod().getName().equals("getPost")) {
            return true;
        }

        // 토큰 정제
        String tokenValue = request.getHeader(JwtUtil.AUTHORIZATION_HEADER);
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)) {
            return false;
        }

        // 토큰에서 아이디 값 가져옴
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String username = info.getSubject();

        // 회원 DB에 존재하는 지 확인
        if(!userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("없는 회원입니다.");
        }

        // username을 전달
        request.setAttribute("username", username);

        return true;
    }


}
