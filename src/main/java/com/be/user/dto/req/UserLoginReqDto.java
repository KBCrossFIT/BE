package com.be.user.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public class UserLoginReqDto {

    private String userID;
    private String userPassword;
}
