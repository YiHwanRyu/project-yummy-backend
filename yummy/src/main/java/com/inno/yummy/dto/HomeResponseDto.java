package com.inno.yummy.dto;

import com.inno.yummy.entity.Post;

import lombok.Getter;

@Getter
public class HomeResponseDto {
    private Long id;
    private String shopname;
    private String region;
    private String sort;
    private String imgurl;

    public HomeResponseDto(Post post) {
        this.id = post.getId();
        this.shopname = post.getShopname();
        this.region = post.getRegion();
        this.sort = post.getSort();
        this.imgurl = post.getImgurl();
    }
}
