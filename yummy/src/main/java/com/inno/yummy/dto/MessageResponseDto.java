package com.inno.yummy.dto;

import lombok.Getter;

@Getter
public class MessageResponseDto {
    private String statusCode;
    private boolean ok;

    public MessageResponseDto(String statusCode, boolean ok) {
        this.statusCode = statusCode;
        this.ok = ok;
    }
}
