package com.inno.yummy.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String shopname;
    private String region;
    private String sort;
    private String address;
    private String content;
    private String imgurl;
}
