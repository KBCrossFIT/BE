package com.be.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {


    /**
     * User Controller
     */
    // 200 OK

    MEMBER_LOGIN(OK, "회원이 로그인했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
