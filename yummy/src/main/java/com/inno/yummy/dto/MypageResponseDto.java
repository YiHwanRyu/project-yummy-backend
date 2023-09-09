package com.inno.yummy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MypageResponseDto {

    private List<HomeResponseDto> postList;

    private List<CommentResponseDto> commentList;

}
