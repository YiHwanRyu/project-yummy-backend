package com.inno.yummy.dto;

import com.inno.yummy.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String shopname;
    private String region;
    private String sort;
    private String username;
    private String address;
    private String content;
    private String imgurl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.shopname = post.getShopname();
        this.region = post.getRegion();
        this.sort = post.getSort();
        this.username = post.getUsername();
        this.address = post.getAddress();
        this.content = post.getContent();
        this.imgurl = post.getImgurl();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList().stream().map(CommentResponseDto::new).toList();

    }
}
