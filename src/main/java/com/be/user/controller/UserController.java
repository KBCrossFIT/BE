package com.be.user.controller;


import com.be.common.dto.DefaultResDto;
import com.be.user.domain.User;
import com.be.user.dto.req.UserLoginReqDto;
import com.be.user.dto.req.UserRegisterReqDto;
import com.be.user.dto.res.UserDefaultResDto;
import com.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.be.common.code.SuccessCode.USER_LOGIN;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public String tesss() {
        log.info("aget");
        return "tess";
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRegisterReqDto reqDto) {

        log.info("register = " + reqDto.toString());
        int state = userService.registerUser(reqDto);

        if (state == 1) {
            return "success";
        }
        return "fail";
    }



    @PostMapping("/login")
    public ResponseEntity<DefaultResDto<Object>> login(@RequestBody @Valid UserLoginReqDto userLoginReqDto) {
        User user = userService.login(userLoginReqDto);

        UserDefaultResDto response = new UserDefaultResDto(user);
        log.info(response.toString());
        return ResponseEntity.status(USER_LOGIN.getHttpStatus())
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(USER_LOGIN.name())
                        .responseMessage(USER_LOGIN.getMessage())
                        .data(response)
                        .build());
    }
}
