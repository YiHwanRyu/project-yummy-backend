package com.inno.yummy.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CheckUsernameRequestDto {

    // 소문자 or 영문 총 3~10자
    @Pattern(regexp = "[a-z0-9]{3,10}")
    private String username;
}
