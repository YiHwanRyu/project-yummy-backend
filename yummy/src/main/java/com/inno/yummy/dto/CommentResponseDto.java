package com.inno.yummy.dto;

import java.time.LocalDateTime;

import com.inno.yummy.entity.Comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.modifiedAt = comment.getModifiedAt();
    }

}
