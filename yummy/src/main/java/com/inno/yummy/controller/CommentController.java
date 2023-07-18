package com.inno.yummy.controller;

import com.inno.yummy.dto.CommentRequestDto;
import com.inno.yummy.dto.MessageResponseDto;
import com.inno.yummy.dto.UpdateCommentRequestDto;
import com.inno.yummy.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comments")
    public MessageResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(commentRequestDto, request.getAttribute("username").toString());
    }

    @PutMapping("/comments/{commentId}")
    public MessageResponseDto updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.updateComment(commentId, commentRequestDto, request.getAttribute("username").toString());
    }

    @DeleteMapping("/comments/{commentId}")
    public MessageResponseDto deleteComment(@PathVariable Long commentId, HttpServletRequest request ){
        return commentService.deleteComment(commentId, request.getAttribute("username").toString());
    }
}
