package com.be.member.controller;


import com.be.common.dto.DefaultResDto;
import com.be.member.domain.Member;
import com.be.member.dto.req.MemberLoginReqDto;
import com.be.member.dto.req.MemberRegisterReqDto;
import com.be.member.dto.res.MemberDefaultResDto;
import com.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.be.common.code.SuccessCode.MEMBER_LOGIN;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public String tesss() {
        log.info("aget");
        return "tess";
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid MemberRegisterReqDto reqDto) {

        log.info("register = " + reqDto.toString());
        int state = memberService.registerMember(reqDto);

        if (state == 1) {
            return "success";
        }
        return "fail";
    }



    @PostMapping("/login")
    public ResponseEntity<DefaultResDto<Object>> login(@RequestBody @Valid MemberLoginReqDto memberLoginReqDto) {
        Member member = memberService.login(memberLoginReqDto);

        MemberDefaultResDto response = new MemberDefaultResDto(member);
        log.info(response.toString());
        return ResponseEntity.status(MEMBER_LOGIN.getHttpStatus())
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(MEMBER_LOGIN.name())
                        .responseMessage(MEMBER_LOGIN.getMessage())
                        .data(response)
                        .build());
    }
}
