package com.inno.yummy.controller;

import com.inno.yummy.dto.HomeResponseDto;
import com.inno.yummy.dto.MessageResponseDto;
import com.inno.yummy.dto.PostRequestDto;
import com.inno.yummy.dto.PostResponseDto;
import com.inno.yummy.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/posts")
    public List<HomeResponseDto> getHomePosts() {
        return postService.getHomePosts();
    }

    @GetMapping("/posts/params")
    public List<HomeResponseDto> getHomePostsWant(@RequestParam(required = false) String region,
                                                      @RequestParam(required = false) String sort) {
        return postService.getHomePostsByWant(region, sort);
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PostMapping("/posts")
    public MessageResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.createPost(postRequestDto, request.getAttribute("username").toString());
    }

    @PutMapping("/posts/{postId}")
    public MessageResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.updatePost(postId, postRequestDto, request.getAttribute("username").toString());
    }

    @DeleteMapping("/posts/{postId}")
    public MessageResponseDto deletePost(@PathVariable Long postId, HttpServletRequest request) {
        return postService.deletePost(postId, request.getAttribute("username").toString());
    }

}
